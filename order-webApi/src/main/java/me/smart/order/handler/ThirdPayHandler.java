package me.smart.order.handler;

import me.smart.order.api.PaymentInfo;
import me.smart.order.api.RefundInfo;
import me.smart.order.exception.BusinessException;
import me.smart.order.model.PaymentOrder;
import me.smart.order.model.PaymentRecord;
import me.smart.order.model.PaymentRefund;

import java.util.Map;

/**
 * Created by zhangxiong on 16/3/19.
 */
public interface ThirdPayHandler {
    /**
     * 推送订单及支付信息到第三方
     *
     * @return
     * @throws Exception
     */
    PaymentRecord handlePush(PaymentOrder order, PaymentInfo paymentInfo, Map<String, Object> ext) throws BusinessException;

    /**
     * 更新支付流水状态，将流水置成功
     * 是第三发回调后被调用
     *
     * @param order
     * @param paymentRecord
     */
    void handleNotice(PaymentOrder order, PaymentRecord paymentRecord) throws BusinessException;


    /**
     * 取消订单
     * 如果有支付成功的流水 则执行退款
     * 修改订单状态
     *
     * @param order
     * @param paymentRecord
     * @return
     */
    RefundInfo handleCancel(PaymentOrder order, PaymentRecord paymentRecord) throws BusinessException;


    /**
     * 申请退款，在BaseThirdPayHandler实现
     * 主要落退款流水，
     * 然后实现根据不同的支付方式来调用不同的代码在子类实现(使用模版模式)
     *
     * @param order
     * @param
     * @return
     */
    RefundInfo handleRefund(PaymentOrder order,PaymentRecord paymentRecord, PaymentRefund paymentRefund) throws BusinessException;

    /**
     * 支付查询
     */
    void handlePayQuery() throws BusinessException;

    /**
     * 退款查询
     */
    void handleRefundQuery() throws BusinessException;
}
