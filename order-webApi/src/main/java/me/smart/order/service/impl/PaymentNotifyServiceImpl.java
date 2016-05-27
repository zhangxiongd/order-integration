package me.smart.order.service.impl;

import me.smart.order.business.tenpay.ResponseForWeixin;
import me.smart.order.dao.MenuOrderMapper;
import me.smart.order.dao.PaymentOrderMapper;
import me.smart.order.dao.PaymentRecordMapper;
import me.smart.order.enums.MenuOrderStatus;
import me.smart.order.enums.PaymentOrderStatus;
import me.smart.order.enums.PaymentRecordStatus;
import me.smart.order.enums.ResultCode;
import me.smart.order.exception.BusinessException;
import me.smart.order.model.MenuOrder;
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
    @Resource
    private MenuOrderMapper menuOrderMapper;


    @Override
    public String acceptTenPayNotify(String msg) {
        logger.info("PaymentNotifyServiceImpl acceptTenPayNotify request={}", msg);

        try {
            PaymentRecord paymentRecord = acceptTenpayNotify.parse(msg);
            logger.info("PaymentNotifyServiceImpl acceptTenPayNotify paymentRecord={}", paymentRecord);
            this.processNotify(paymentRecord);
            notifyMerchant(paymentRecord.getOutTradeNo());
            logger.info("接受微信支付通知结束，transactionId={}", paymentRecord.getTransactionId());
            return new ResponseForWeixin("SUCCESS", "OK").toString();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseForWeixin("FAIL", e.getMessage()).toString();
        }
    }


    @Override
    @Transactional
    public PaymentOrder processNotify(PaymentRecord paymentRecord) throws Exception{
        PaymentOrder paymentOrder = paymentOrderMapper.selectByOutTradeNo(paymentRecord.getMemberId(), paymentRecord.getOutTradeNo());
        if(paymentOrder == null){
            throw new BusinessException(ResultCode.ORDER_NOT_EXIST_ERROR);
        }
        //获取菜订单
        MenuOrder menuOrder = menuOrderMapper.selectByMIdAndOrderNoAndMemberId(paymentOrder.getMemberId(), paymentOrder.getMenuOrderNo(), paymentOrder.getMerchantId());
        if(menuOrder == null ){
               throw new BusinessException(ResultCode.ORDER_NOT_EXIST_ERROR);
        }
        //修改菜订单状态
        PaymentRecord paymentRecordUpdate = new PaymentRecord();
        paymentRecordUpdate.setId(paymentRecord.getId());
        paymentRecordUpdate.setPayStatus(PaymentRecordStatus.SUCCESS.getCode());

        //修改订单状态
        PaymentOrder paymentOrderUpdate = new PaymentOrder();
        paymentOrderUpdate.setId(paymentOrder.getId());
        paymentOrderUpdate.setOrderStatus(PaymentOrderStatus.SUCCESS.getStatus());
        paymentOrderMapper.update(paymentOrderUpdate);
        paymentRecordMapper.update(paymentRecordUpdate);
        menuOrderMapper.updateByStatus(paymentOrder.getMerchantId(),menuOrder.getMenuOrderNo(), MenuOrderStatus.PENDING.getStatus());
        return paymentOrder;
    }

    /**
     * 通过mq通知merchant
     * @param outTradeNo
     */
    private void notifyMerchant(String outTradeNo){
        try{
          //用mq通知商户app来订单了,传递outTradeNo,order-merchant收到信息后则给APP推送消息
//        MessagePostProcessor postProcessor = new MessagePostProcessor() {
//            @Override
//            public Message postProcessMessage(Message message) throws AmqpException {
//                message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);
//                return message;
//            }
//        };
            rabbitTemplate.convertAndSend(outTradeNo);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
        }
    }

    /**
     * 接收支付宝回调通知
     * @param msg
     * @return
     */
    @Override
    public String acceptALiPayNotify(String msg) {
        return null;
    }
}
