package me.smart.order.api;

import lombok.ToString;
import me.smart.order.enums.PaymentWay;
import me.smart.order.enums.TradeType;

/**
 *
 */
@ToString
public class PaymentInfo {

    /**
     * 支付小类
     */
    protected PaymentWay paymentWay;

    /**
     * 支付方式
     */
    protected TradeType tradeType;

    protected int payAmount;

    public PaymentWay getPaymentWay() {
        return paymentWay;
    }

    public void setPaymentWay(PaymentWay paymentWay) {
        this.paymentWay = paymentWay;
    }

    public TradeType getTradeType() {
        return tradeType;
    }

    public void setTradeType(TradeType tradeType) {
        this.tradeType = tradeType;
    }

    public int getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(int payAmount) {
        this.payAmount = payAmount;
    }
}
