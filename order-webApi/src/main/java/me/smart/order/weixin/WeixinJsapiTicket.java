package me.smart.order.weixin;

import java.util.Date;

/**
 * Created by zhangxiong on 16/1/16.
 */
public class WeixinJsapiTicket {
    private int errorCode;

    private String errorMsg;

    private String ticket;

    private Date applyDate;

    private int expires_in;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public Date getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(Date applyDate) {
        this.applyDate = applyDate;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }
}
