package me.smart.order.dao;

import me.smart.order.model.PaymentRefund;

/**
 * Created by zhangxiong on 2016-05-25.
 */
public interface PaymentRefundMapper {
    int insert(PaymentRefund paymentRefund);
    int update(PaymentRefund paymentRefund);
}
