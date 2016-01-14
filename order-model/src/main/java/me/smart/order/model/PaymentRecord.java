package me.smart.order.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by zhangxiong on 15/12/30.
 */
public class PaymentRecord implements Serializable {
    private Long id;

    //流水好
    private String transactionId;
    private Long memberId;
    private Long merchantId;
    //支付订单号
    private String outTradeNo;
    //支付方式
    private String paymentWay;
    //交易类型
    private String tradeType;
    //支付金额
    private BigDecimal totalAmount;
    //退款金额
    private BigDecimal refundedAmount;
    //支付状态
    private Integer payStatus;
    //支付成功时间
    private Date paySuccessTime;
    //微信预支付id
    private String prepayId;

    //第三方支付流水号
    private String outTransactionId;
    //第三方买家账号
    private String thirdUserId;
    //返回码
    private String returnCode;
    //
    private String returnMsg;

    private Boolean isDelete;

    private Date createdAt;
    private Date updatedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }


    public String getOutTransactionId() {
        return outTransactionId;
    }

    public void setOutTransactionId(String outTransactionId) {
        this.outTransactionId = outTransactionId;
    }

    public String getReturnMsg() {
        return returnMsg;
    }

    public void setReturnMsg(String returnMsg) {
        this.returnMsg = returnMsg;
    }

    public String getPaymentWay() {
        return paymentWay;
    }

    public void setPaymentWay(String paymentWay) {
        this.paymentWay = paymentWay;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getRefundedAmount() {
        return refundedAmount;
    }

    public void setRefundedAmount(BigDecimal refundedAmount) {
        this.refundedAmount = refundedAmount;
    }

    public Integer getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
    }

    public Date getPaySuccessTime() {
        return paySuccessTime;
    }

    public void setPaySuccessTime(Date paySuccessTime) {
        this.paySuccessTime = paySuccessTime;
    }

    public String getPrepayId() {
        return prepayId;
    }

    public void setPrepayId(String prepayId) {
        this.prepayId = prepayId;
    }

    public String getThirdUserId() {
        return thirdUserId;
    }

    public void setThirdUserId(String thirdUserId) {
        this.thirdUserId = thirdUserId;
    }

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }


    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }


    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("PaymentRecord{");
        sb.append("id=").append(id);
        sb.append(", transactionId='").append(transactionId).append('\'');
        sb.append(", memberId=").append(memberId);
        sb.append(", merchantId=").append(merchantId);
        sb.append(", outTradeNo='").append(outTradeNo).append('\'');
        sb.append(", paymentWay='").append(paymentWay).append('\'');
        sb.append(", tradeType='").append(tradeType).append('\'');
        sb.append(", totalAmount=").append(totalAmount);
        sb.append(", refundedAmount=").append(refundedAmount);
        sb.append(", payStatus=").append(payStatus);
        sb.append(", paySuccessTime=").append(paySuccessTime);
        sb.append(", prepayId='").append(prepayId).append('\'');
        sb.append(", outTransactionId='").append(outTransactionId).append('\'');
        sb.append(", thirdUserId='").append(thirdUserId).append('\'');
        sb.append(", returnCode='").append(returnCode).append('\'');
        sb.append(", returnMsg='").append(returnMsg).append('\'');
        sb.append(", isDelete=").append(isDelete);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", updatedAt=").append(updatedAt);
        sb.append('}');
        return sb.toString();
    }
}


