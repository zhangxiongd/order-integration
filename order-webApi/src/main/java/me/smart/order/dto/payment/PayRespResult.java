package me.smart.order.dto.payment;

import lombok.ToString;

import java.io.Serializable;

/**
 * Created by zhangxiong on 16/3/19.
 */
@ToString
public class PayRespResult implements Serializable {
    private static final long serialVersionUID = -5624372270633437024L;
    private int payStatus;
    private String payData;
    private String payUrl;

    public int getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(int payStatus) {
        this.payStatus = payStatus;
    }

    public String getPayData() {
        return payData;
    }

    public void setPayData(String payData) {
        this.payData = payData;
    }

    public String getPayUrl() {
        return payUrl;
    }

    public void setPayUrl(String payUrl) {
        this.payUrl = payUrl;
    }

}
