package me.smart.order.dto.payment;

import java.io.Serializable;

public class RefundQueryRespResult implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 2380260461717952890L;
    private String outRefundNo = "";//商户退款单号
    private String refundStatus = "";//退款状态
    private int refundFee = 0;//退款金额

    private String batchNo;
    private String tradeNo;

    private String thirdRefundNo;

    public String getOutRefundNo() {
        return outRefundNo;
    }

    public void setOutRefundNo(String outRefundNo) {
        this.outRefundNo = outRefundNo;
    }

    public String getRefundStatus() {
        return refundStatus;
    }

    public void setRefundStatus(String refundStatus) {
        this.refundStatus = refundStatus;
    }

    public int getRefundFee() {
        return refundFee;
    }

    public void setRefundFee(int refundFee) {
        this.refundFee = refundFee;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public String getThirdRefundNo() {
        return thirdRefundNo;
    }

    public void setThirdRefundNo(String thirdRefundNo) {
        this.thirdRefundNo = thirdRefundNo;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("RefundQueryRespResult [outRefundNo=");
        builder.append(outRefundNo);
        builder.append(", refundStatus=");
        builder.append(refundStatus);
        builder.append(", refundFee=");
        builder.append(refundFee);
        builder.append(", batchNo=");
        builder.append(batchNo);
        builder.append(", tradeNo=");
        builder.append(tradeNo);
        builder.append(", thirdRefundNo=");
        builder.append(thirdRefundNo);
        builder.append("]");
        return builder.toString();
    }

}
