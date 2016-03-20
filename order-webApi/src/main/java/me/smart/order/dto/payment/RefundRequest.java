package me.smart.order.dto.payment;

/**
 * Created by zhangxiong on 16/3/20.
 */
public class RefundRequest {
    protected Long corpId;
    private String payTransId;
    private String payMethod;
    private String payTradeType;
    /**
     * 支付流水金额
     */
    private int orderAmount;
    /**
     * 退款金额
     */
    private int refundAmount;
    private String deviceInfo;
    /**
     * 记录退款来源
     */
    private String source;
    private String refundFeeCurrency;
    private String userId;
    private String outTransId;
    /**
     * 记录退款操作人员（是手动退款）
     */
    private String operator;


    public Long getCorpId() {
        return corpId;
    }

    public void setCorpId(Long corpId) {
        this.corpId = corpId;
    }

    public String getPayTransId() {
        return payTransId;
    }

    public void setPayTransId(String payTransId) {
        this.payTransId = payTransId;
    }

    public String getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod;
    }

    public String getPayTradeType() {
        return payTradeType;
    }

    public void setPayTradeType(String payTradeType) {
        this.payTradeType = payTradeType;
    }

    public int getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(int orderAmount) {
        this.orderAmount = orderAmount;
    }

    public int getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(int refundAmount) {
        this.refundAmount = refundAmount;
    }

    public String getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(String deviceInfo) {
        this.deviceInfo = deviceInfo;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getRefundFeeCurrency() {
        return refundFeeCurrency;
    }

    public void setRefundFeeCurrency(String refundFeeCurrency) {
        this.refundFeeCurrency = refundFeeCurrency;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOutTransId() {
        return outTransId;
    }

    public void setOutTransId(String outTransId) {
        this.outTransId = outTransId;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }
}
