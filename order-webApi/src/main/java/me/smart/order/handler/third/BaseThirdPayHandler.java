package me.smart.order.handler.third;

import me.smart.order.api.PaymentInfo;
import me.smart.order.api.RefundInfo;
import me.smart.order.dto.payment.RefundRequest;
import me.smart.order.dto.payment.RefundRespResult;
import me.smart.order.exception.BusinessException;
import me.smart.order.handler.ThirdPayHandler;
import me.smart.order.model.PaymentOrder;
import me.smart.order.model.PaymentRecord;
import me.smart.order.model.PaymentRefund;
import me.smart.order.service.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
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
        return null;
    }

    @Override
    public RefundInfo handleRefund(PaymentOrder order, PaymentRefund paymentRefund) throws BusinessException {
        return null;
    }

    /**
     * 由子类去实现各自的退款业务
     *
     * @param refundRequest
     * @param paymentRefund
     * @return
     */
    protected abstract RefundRespResult refund(RefundRequest refundRequest, PaymentRefund paymentRefund);

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

    protected void updatePaymentRecordNotPay(PaymentOrder paymentOrder) {

    }
}
