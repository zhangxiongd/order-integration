package me.smart.order.api;

import me.smart.order.enums.PaymentWay;
import me.smart.order.enums.RefundStatus;
import me.smart.order.enums.TradeType;

/**
 * Created by zhangxiong on 16/3/19.
 */
public class RefundInfo {
    /**
     * 支付平台流水号
     */
    private String payTransId;

    /**
     * 退款单号
     */
    private String refundNo;

    /**
     * 退款金额
     */
    private int refundAmount;

    private PaymentWay payMethod;

    private TradeType tradeType;

    /**
     * 退款状态
     */
    private RefundStatus refundStatus;

    /**
     * 第三方支付交易单号
     */
    private String outTransId;

    /**
     * 退款批次号(支付宝)
     */
    private String outBatchNo;

    /**
     * 第三方退款单号
     */
    private String outRefundNo;

    public String getPayTransId() {
        return payTransId;
    }

    public void setPayTransId(String payTransId) {
        this.payTransId = payTransId;
    }

    public String getRefundNo() {
        return refundNo;
    }

    public void setRefundNo(String refundNo) {
        this.refundNo = refundNo;
    }

    public PaymentWay getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(PaymentWay payMethod) {
        this.payMethod = payMethod;
    }

    public TradeType getTradeType() {
        return tradeType;
    }

    public void setTradeType(TradeType tradeType) {
        this.tradeType = tradeType;
    }

    public String getOutTransId() {
        return outTransId;
    }

    public void setOutTransId(String outTransId) {
        this.outTransId = outTransId;
    }

    public String getOutBatchNo() {
        return outBatchNo;
    }

    public void setOutBatchNo(String outBatchNo) {
        this.outBatchNo = outBatchNo;
    }

    public String getOutRefundNo() {
        return outRefundNo;
    }

    public void setOutRefundNo(String outRefundNo) {
        this.outRefundNo = outRefundNo;
    }

    public RefundStatus getRefundStatus() {
        return refundStatus;
    }

    public void setRefundStatus(RefundStatus refundStatus) {
        this.refundStatus = refundStatus;
    }

    public int getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(int refundAmount) {
        this.refundAmount = refundAmount;
    }

}
