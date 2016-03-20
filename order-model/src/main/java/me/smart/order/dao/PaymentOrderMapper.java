package me.smart.order.dao;

import me.smart.order.model.PaymentOrder;

/**
 * Created by zhangxiong on 15/12/31.
 */
public interface PaymentOrderMapper {
    int insert(PaymentOrder paymentOrder);
}
