package me.smart.order.api.service;

import me.smart.order.api.Result;

/**
 * Created by zhangxiong on 16/1/14.
 */
public interface PaymentService {
    /**
     * 预支付
     */
    public Result prepay();
}
