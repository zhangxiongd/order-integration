package me.smart.order.enums;

import java.util.Arrays;
import java.util.List;

/**
 * Created by zhangxiong on 16/3/19.
 */
public enum PaymentWay {
    TEN_PAY("微信支付", PayCatalog.THIRD_PAY);

    private String paymentWay;
    private PayCatalog payCatalog;


    public static final List<PaymentWay> THIRD_PAY_METHODS = Arrays.asList(TEN_PAY);


    PaymentWay(String paymentWay, PayCatalog payCatalog) {
        this.paymentWay = paymentWay;
        this.payCatalog = payCatalog;
    }

    public static PaymentWay parse(String name) {
        try {
            return PaymentWay.valueOf(name);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }


    public String getPaymentWay() {
        return paymentWay;
    }

    public PayCatalog getPayCatalog() {
        return payCatalog;
    }
}
