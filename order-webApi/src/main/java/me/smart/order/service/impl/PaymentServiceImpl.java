package me.smart.order.service.impl;

import me.smart.order.api.PaymentInfo;
import me.smart.order.api.Result;
import me.smart.order.api.member.Request.CancelRequest;
import me.smart.order.api.member.Response.PayResult;
import me.smart.order.dao.*;
import me.smart.order.enums.*;
import me.smart.order.exception.BusinessException;
import me.smart.order.handler.ThirdPayHandler;
import me.smart.order.model.MemberUnion;
import me.smart.order.model.MenuOrder;
import me.smart.order.model.Merchant;
import me.smart.order.model.PaymentOrder;
import me.smart.order.model.PaymentRecord;
import me.smart.order.service.PaymentService;
import me.smart.order.util.OrderNoUtils;
import me.smart.order.util.UUIDGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhangxiong on 16/1/14.
 */
@Service("paymentService")
public class PaymentServiceImpl implements PaymentService {

    private Logger logger = LoggerFactory.getLogger(PaymentServiceImpl.class);

    @Value("pay_dead_line")
    private int payDeadLine;

    @Resource
    private Map<PaymentWay, ThirdPayHandler> thirdPayHandlers;

    @Resource
    private PaymentOrderMapper paymentOrderMapper;

    @Resource
    private MemberUnionMapper memberUnionMapper;

    @Resource
    private MerchantMapper merchantMapper;

    @Resource
    private PaymentRecordMapper paymentRecordMapper;

    @Resource
    private MenuOrderMapper menuOrderMapper;


    /**
     * 根据MenuOrder下支付订单
     * 下菜品订单和支付订单依然分开设计，分两步走
     *
     * @param menuOrder
     */
    @Override
    @Transactional
    public PaymentOrder placeOrder(MenuOrder menuOrder) throws BusinessException {
        logger.info("开始落地支付订单。 menuOrder={}", menuOrder);
        Merchant merchant = merchantMapper.getMerchantById(menuOrder.getMemberId());
        if (merchant == null) {
            throw new BusinessException(ResultCode.MERCHANT_NOT_EXIST_ERROR, ResultCode.MERCHANT_NOT_EXIST_ERROR.getDesc());
        }

        String outTradeNo = OrderNoUtils.getOrderNo(menuOrder.getMemberId().toString());
        String orderName = merchant.getMerchantName() + "订单号 " + outTradeNo;
        PaymentOrder paymentOrder = new PaymentOrder(menuOrder.getMerchantId(), menuOrder.getMemberId(),
                outTradeNo, menuOrder.getMenuOrderNo(), menuOrder.getTotalAmount(), orderName, payDeadLine);
        paymentOrder.setProductBody(orderName);
        paymentOrderMapper.insert(paymentOrder);
        logger.info("落地支付订单成功");
        return paymentOrder;
    }

    /**
     * 落地支付流水
     * 根据支付订单落地流水
     * 此方法暂只支持微信
     *
     * @param paymentOrder
     */
    @Override
    @Transactional
    public Result transactByOrder(PaymentOrder paymentOrder) throws BusinessException {
        logger.info("开始落地支付流水, paymentOrder={}", paymentOrder);
        //获取服务号的openId(一定要是服务号的openId 不然微信支付失败)
        MemberUnion memberUnion = memberUnionMapper.selectByMemberIdAndSource(paymentOrder.getMemberId(),
                MemberUnionEnums.TEN_SERVICE.getSource());
        if (memberUnion == null) {
            throw new BusinessException(ResultCode.MEMBER_NOT_EXIST_ERROR, ResultCode.MEMBER_NOT_EXIST_ERROR.getDesc());
        }

        PaymentWay paymentWay = PaymentWay.TEN_PAY;
        PaymentInfo paymentInfo = new PaymentInfo();
        paymentInfo.setPayAmount(paymentOrder.getTotalAmount().intValue());
        paymentInfo.setPaymentWay(PaymentWay.TEN_PAY);
        paymentInfo.setTradeType(TradeType.JSAPI);
        Map<String, Object> ext = new HashMap<>();
        ext.put("openId", memberUnion.getOpenId());

        //获取支付的handler
        ThirdPayHandler thirdPayHandler = getThirdPayHandler(paymentWay);

        try {
            PaymentRecord paymentRecord = thirdPayHandler.handlePush(paymentOrder, paymentInfo, ext);
            PayResult payResult = new PayResult(paymentOrder.getMerchantId().toString(),
                    paymentOrder.getMerchantId().toString(), paymentOrder.getOutTradeNo(),
                    paymentRecord.getTransactionId(), paymentOrder.getOrderData());
            return Result.createResult(payResult, ext);

        } catch (Exception e) {
            logger.error("发起支付失败,errorMessage={}", e.getMessage(), e);
            return Result.errorResult(ResultCode.SYSTEM_ERROR);
        }

    }


    /**
     * 将支付流水更新为成功状态
     * 将paySuccessTime设置成当前数据库系统时间
     *
     * @param paymentRecord
     */
    @Override
    public void updatePaymentRecordSuccess(PaymentRecord paymentRecord) {
        logger.info("支付流水成功更新记录,paymentRecord={}", paymentRecord);

    }

    /**
     * 将支付流水更新为待支付，并将第三发返回的openId等信息更新到数据库
     *
     * @param paymentRecord
     */
    @Override
    public void updatePaymentRecordNotPay(PaymentRecord paymentRecord) {
        logger.info("保存第三方返回的openId等信息，payment");
    }

    /**
     * 根据merchant_id  member_id out_trade_no payment_way trade_type
     * 查找相应的支付流水
     *
     * @param paymentOrder
     * @param paymentWay
     * @param tradeType
     * @return
     */
    @Override
    public PaymentRecord selectByMidAndNoAndPayType(PaymentOrder paymentOrder, PaymentWay paymentWay, TradeType tradeType) {
        return null;
    }

    /**
     * 根据订单 以及 支付信息落地支付流水
     *
     * @param paymentOrder
     * @param paymentInfo
     * @return
     */
    @Override
    public PaymentRecord insertPaymentRecord(PaymentOrder paymentOrder, PaymentInfo paymentInfo) {
        PaymentRecord paymentRecord = new PaymentRecord();
        paymentRecord.setMemberId(paymentOrder.getMemberId());
        paymentRecord.setMerchantId(paymentOrder.getMerchantId());
        paymentRecord.setTransactionId(UUIDGenerator.get32UUID());
        paymentRecord.setOutTradeNo(paymentOrder.getOutTradeNo());
        paymentRecord.setPaymentWay(paymentInfo.getPaymentWay().getPaymentWay());
        paymentRecord.setTradeType(paymentInfo.getTradeType().name());
        paymentRecord.setTotalAmount(new BigDecimal(paymentInfo.getPayAmount()));
        paymentRecord.setRefundedAmount(new BigDecimal(0));
        paymentRecord.setPayStatus(PaymentRecordStatus.NOTPAY.getCode());
        paymentRecord.setIsDelete(false);
        Date now = new Date();
        paymentRecord.setCreatedAt(now);
        paymentRecord.setUpdatedAt(now);
        paymentRecordMapper.insert(paymentRecord);
        return paymentRecord;
    }


    private ThirdPayHandler getThirdPayHandler(PaymentWay paymentWay) throws BusinessException {
        ThirdPayHandler thirdPayHandler = thirdPayHandlers.get(paymentWay);
        if (thirdPayHandler == null) {
            throw new BusinessException(ResultCode.SYSTEM_ERROR, "未配置或不支持该支付方式");
        }
        return thirdPayHandler;
    }


    /**
     * @param cancelRequest 订单取消请求
     */
    @Override
    public void cancel(CancelRequest cancelRequest) throws Exception {
        PaymentOrder paymentOrder = paymentOrderMapper.selectByMenuOrderNo(Long.valueOf(cancelRequest.getMemberId()),cancelRequest.getMenuOrderNo());
        MenuOrder menuOrder = menuOrderMapper.selectByMenuOrderNo(Long.valueOf(cancelRequest.getMerchantId()),cancelRequest.getMenuOrderNo());

        if(menuOrder.getOrderStatus() != MenuOrderStatus.PENDING.getStatus()){
            throw new BusinessException(ResultCode.ORDER_STATUS_ERROR);
        }
        logger.info("PaymentServiceImpl cancel menu_order_no={}, out_trade_no={}", paymentOrder.getMenuOrderNo(), paymentOrder.getOutTradeNo());
        List<PaymentRecord> paymentRecordList = paymentRecordMapper.selectByMeIdAndNo(paymentOrder.getMemberId(), paymentOrder.getOutTradeNo());
        ThirdPayHandler thirdPayHandler = getThirdPayHandler(PaymentWay.TEN_PAY);
        thirdPayHandler.handleCancel(paymentOrder, paymentRecordList.get(0));
    }
}
