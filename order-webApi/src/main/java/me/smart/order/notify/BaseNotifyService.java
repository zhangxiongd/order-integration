package me.smart.order.notify;

import me.smart.order.model.PaymentRecord;

/**
 * Created by zhangxiong on 16/5/16.
 */
public interface BaseNotifyService {
    public PaymentRecord parse(String msg) throws Exception;

    public Object msgCheck(String msg) throws Exception;
}
