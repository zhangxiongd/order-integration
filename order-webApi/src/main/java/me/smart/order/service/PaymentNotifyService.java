package me.smart.order.service;

import me.smart.order.model.PaymentOrder;
import me.smart.order.model.PaymentRecord;

/**
 * Created by zhangxiong on 16/5/14.
 */
public interface PaymentNotifyService {
    String acceptTenPayNotify(String msg);

    String acceptALiPayNotify(String msg);

    PaymentOrder processNotify(PaymentRecord paymentRecord);
}
