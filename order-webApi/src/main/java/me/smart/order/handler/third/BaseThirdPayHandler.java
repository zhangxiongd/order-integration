package me.smart.order.handler.third;

import me.smart.order.api.PaymentInfo;
import me.smart.order.api.RefundInfo;
import me.smart.order.api.Result;
import me.smart.order.dao.PaymentOrderMapper;
import me.smart.order.dao.PaymentRecordMapper;
import me.smart.order.dao.PaymentRefundMapper;
import me.smart.order.dto.payment.RefundRequest;
import me.smart.order.dto.payment.RefundRespResult;
import me.smart.order.enums.*;
import me.smart.order.exception.BusinessException;
import me.smart.order.handler.ThirdPayHandler;
import me.smart.order.model.PaymentOrder;
import me.smart.order.model.PaymentRecord;
import me.smart.order.model.PaymentRefund;
import me.smart.order.service.PaymentService;
import me.smart.order.util.UUIDGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhangxiong on 16/3/19.
 */
public abstract class BaseThirdPayHandler implements ThirdPayHandler {

    public static final String KEY_PAY_DATA = "payData";
    public static final String KEY_PAY_URL = "payUrl";

    @Resource
    private Logger logger = LoggerFactory.getLogger(BaseThirdPayHandler.class);

    @Resource
    private PaymentService paymentService;
    @Resource
    private PaymentRefundMapper paymentRefundMapper;
    @Resource
    private PaymentRecordMapper paymentRecordMapper;
    @Resource
    private PaymentOrderMapper paymentOrderMapper;

    /**
     * 推送订单及支付信息到第三方
     *
     * @return
     * @throws Exception
     */
    @Override
    public PaymentRecord handlePush(PaymentOrder order, PaymentInfo paymentInfo, Map<String, Object> ext) throws BusinessException {
        return pushPayment(order, paymentInfo, ext);
    }

    /**
     * 每个支付方式的支付业务
     * 由子类去实现自己的具体支付业务
     *
     * @param order
     * @param paymentInfo
     * @param ext
     * @return
     * @throws BusinessException
     */
    protected abstract PaymentRecord pushPayment(PaymentOrder order, PaymentInfo paymentInfo, Map<String, Object> ext) throws BusinessException;

    @Override
    public void handleNotice(PaymentOrder order, PaymentRecord paymentRecord) throws BusinessException {
        logger.info("支付流水成功， paymentOrder={}, paymentRecord={}", order, paymentRecord);
        paymentService.updatePaymentRecordSuccess(paymentRecord);
    }

    @Override
    public RefundInfo handleCancel(PaymentOrder order, PaymentRecord paymentRecord) throws BusinessException {
        PaymentOrder orderUpdate = new PaymentOrder();
        orderUpdate.setId(order.getId());
        orderUpdate.setOrderStatus(PaymentOrderStatus.CANCEL.getStatus());
        PaymentRecord recordUpdate = new PaymentRecord();
        recordUpdate.setId(paymentRecord.getId());
        recordUpdate.setPayStatus(PaymentRecordStatus.REFUND.getCode());
        recordUpdate.setRefundedAmount(paymentRecord.getTotalAmount());
        //给退款数据
        paymentRecord.setPayStatus(PaymentRecordStatus.REFUND.getCode());
        paymentRecord.setRefundedAmount(paymentRecord.getTotalAmount());
        //update order and payment_record
        paymentOrderMapper.update(orderUpdate);
        paymentRecordMapper.update(recordUpdate);
        PaymentRefund paymentRefund = createPaymentRefund(paymentRecord,paymentRecord.getTotalAmount());
        return handleRefund(order,paymentRecord,paymentRefund);
    }

    @Override
    public RefundInfo handleRefund(PaymentOrder order, PaymentRecord paymentRecord, PaymentRefund paymentRefund) throws BusinessException {
        RefundRespResult respResult = null;
        PaymentRefund refund = new PaymentRefund();
        try{
            respResult  = refund(createRefundQuest(paymentRecord,paymentRefund),paymentRefund);
            refund.setId(paymentRefund.getId());
            refund.setRefundStatus(respResult.getApplyRefundStatus());
        }catch (BusinessException e){
            //将此退款流水置失败
            refund.setId(paymentRefund.getId());
            refund.setRefundStatus(RefundStatus.FAIL.getCode());
            throw  e;
        }
        try{
            paymentRefundMapper.update(refund);
        }catch (Exception e){
            logger.error("sql error",e);
            throw new BusinessException(ResultCode.SQL_ERROR);
        }
        return parseRefundInfo(respResult,paymentRecord,paymentRefund);
    }

    private RefundInfo parseRefundInfo(RefundRespResult respResult, PaymentRecord paymentRecord,PaymentRefund paymentRefund){
        RefundInfo refundInfo = new RefundInfo();
        refundInfo.setOutTransId(paymentRecord.getOutTransactionId());
        refundInfo.setRefundAmount(paymentRefund.getRefundAmount().intValue());
        refundInfo.setOutBatchNo(paymentRefund.getRefundBatchNo());
        refundInfo.setOutRefundNo(respResult.getThirdRefundNo());
        refundInfo.setPayMethod(PaymentWay.parse(paymentRecord.getPaymentWay()));
        refundInfo.setRefundStatus(RefundStatus.parse( paymentRefund.getRefundStatus()));
        return refundInfo;
    }

    /**
     * 由子类去实现各自的退款业务
     *
     * @param refundRequest
     * @param paymentRefund
     * @return
     */
    protected abstract RefundRespResult refund(RefundRequest refundRequest, PaymentRefund paymentRefund) throws BusinessException;

    @Override

    public void handlePayQuery() throws BusinessException {

    }

    @Override
    public void handleRefundQuery() throws BusinessException {

    }

    protected Map<String, String> createPayData(Map<String, Object> ext) {
        Map<String, String> payData = new HashMap<>();
        ext.put(KEY_PAY_DATA, payData);

        return payData;
    }

    protected Map<String, String> createPayUrls(Map<String, Object> ext) {
        Map<String, String> payUrls = new HashMap<>();
        ext.put(KEY_PAY_URL, payUrls);

        return payUrls;
    }

    private RefundRequest createRefundQuest(PaymentRecord paymentRecord, PaymentRefund paymentRefund){
        RefundRequest refundRequest = new RefundRequest();
        refundRequest.setOrderAmount(paymentRecord.getTotalAmount().intValue());
        refundRequest.setRefundAmount(paymentRefund.getRefundAmount().intValue());
        refundRequest.setOutTransId(paymentRecord.getOutTransactionId());
        refundRequest.setPayMethod(paymentRecord.getPaymentWay());
        refundRequest.setPayTradeType(paymentRecord.getTradeType());
        refundRequest.setTransactionId(paymentRecord.getTransactionId());
        refundRequest.setOutTradeNo(paymentRecord.getOutTradeNo());
        return refundRequest;
    }

    private PaymentRefund createPaymentRefund(PaymentRecord paymentRecord, BigDecimal refundAmount){
        PaymentRefund paymentRefund = new PaymentRefund();
        paymentRefund.setRefundAmount(refundAmount);
        paymentRefund.setTransactionId(paymentRecord.getTransactionId());
        paymentRefund.setMemberId(paymentRecord.getMemberId());
        paymentRefund.setMerchantId(paymentRecord.getMerchantId());
        paymentRefund.setIsDelete(false);
        paymentRefund.setRefundNo(UUIDGenerator.get32UUID());
        Date now = new Date();
        paymentRefund.setCreatedAt(now);
        paymentRefund.setUpdatedAt(now);
        paymentRefund.setRefundStatus(RefundStatus.PROCESSING.getCode());
        paymentRefundMapper.insert(paymentRefund);
        return paymentRefund;
    }
}
