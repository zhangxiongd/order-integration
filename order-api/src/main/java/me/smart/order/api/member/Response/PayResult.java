package me.smart.order.api.member.Response;

import lombok.ToString;
import me.smart.order.enums.PaymentRecordStatus;

import java.io.Serializable;

/**
 * Created by zhangxiong on 16/3/20.
 */
@ToString
public class PayResult implements Serializable {
    private String merchantId;
    private String memberId;
    private String outTradeNo;
    private String payTransId;
    private PaymentRecordStatus payStatus = PaymentRecordStatus.NOTPAY;
    private String paySuccessTime;
    private String outTransId;
    private String merchantData;
//    private String sign;


    public PayResult() {
    }

    public PayResult(String merchantId, String memberId, String outTradeNo,
                     String payTransId, String merchantData) {
        this.merchantId = merchantId;
        this.memberId = memberId;
        this.outTradeNo = outTradeNo;
        this.payTransId = payTransId;
        this.merchantData = merchantData;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getPayTransId() {
        return payTransId;
    }

    public void setPayTransId(String payTransId) {
        this.payTransId = payTransId;
    }

    public PaymentRecordStatus getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(PaymentRecordStatus payStatus) {
        this.payStatus = payStatus;
    }

    public String getPaySuccessTime() {
        return paySuccessTime;
    }

    public void setPaySuccessTime(String paySuccessTime) {
        this.paySuccessTime = paySuccessTime;
    }

    public String getOutTransId() {
        return outTransId;
    }

    public void setOutTransId(String outTransId) {
        this.outTransId = outTransId;
    }

    public String getMerchantData() {
        return merchantData;
    }

    public void setMerchantData(String merchantData) {
        this.merchantData = merchantData;
    }

//    public String getSign() {
//        return sign;
//    }
//
//    public void setSign(String sign) {
//        this.sign = sign;
//    }
}
