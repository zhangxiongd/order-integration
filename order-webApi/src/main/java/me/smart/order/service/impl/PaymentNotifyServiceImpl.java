package me.smart.order.service.impl;

import me.smart.order.business.tenpay.ResponseForWeixin;
import me.smart.order.dao.PaymentOrderMapper;
import me.smart.order.dao.PaymentRecordMapper;
import me.smart.order.enums.PaymentOrderStatus;
import me.smart.order.enums.PaymentRecordStatus;
import me.smart.order.model.PaymentOrder;
import me.smart.order.model.PaymentRecord;
import me.smart.order.notify.impl.AcceptTenpayNotify;
import me.smart.order.service.PaymentNotifyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by zhangxiong on 16/5/14.
 */
@Service("paymentNotifyService")
public class PaymentNotifyServiceImpl implements PaymentNotifyService {
    private Logger logger = LoggerFactory.getLogger(PaymentNotifyServiceImpl.class);

    @Resource
    private AcceptTenpayNotify acceptTenpayNotify;
    @Resource
    private PaymentOrderMapper paymentOrderMapper;
    @Resource
    private PaymentRecordMapper paymentRecordMapper;
    @Resource
    private RabbitTemplate rabbitTemplate;

    @Override
    public String acceptTenPayNotify(String msg) {
        logger.info("PaymentNotifyServiceImpl acceptTenPayNotify request={}", msg);

        try {
            PaymentRecord paymentRecord = acceptTenpayNotify.parse(msg);
            logger.info("PaymentNotifyServiceImpl acceptTenPayNotify paymentrecord={}", paymentRecord);
            this.processNotify(paymentRecord);
            logger.info("接受微信支付通知结束，transactionId={}", paymentRecord.getTransactionId());
            return new ResponseForWeixin("SUCCESS", "OK").toString();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseForWeixin("FAIL", e.getMessage()).toString();
        }
    }


    @Override
    @Transactional
    public PaymentOrder processNotify(PaymentRecord paymentRecord) {
        PaymentOrder paymentOrder = paymentOrderMapper.selectByOutTradeNo(paymentRecord.getMemberId(), paymentRecord.getOutTradeNo());

        //修改订单状态
        paymentOrder.setOrderStatus(PaymentOrderStatus.SUCCESS.getStatus());
        //修改流水状态
        paymentRecord.setPayStatus(PaymentRecordStatus.SUCCESS.getCode());
        paymentOrderMapper.update(paymentOrder);
        paymentRecordMapper.update(paymentRecord);
        //用mq通知商户app来订单了,传递outTradeNo,order-merchant收到信息后则给APP推送消息
        rabbitTemplate.convertAndSend(paymentOrder.getOutTradeNo());
        return paymentOrder;
    }


    @Override
    public String acceptALiPayNotify(String msg) {
        return null;
    }
}
