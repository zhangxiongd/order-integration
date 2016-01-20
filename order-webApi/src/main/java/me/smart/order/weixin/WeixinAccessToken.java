package me.smart.order.weixin;

import java.util.Date;

/**
 * Created by zhangxiong on 16/1/16.
 */
public class WeixinAccessToken {
    private String accessTokenValue;
    private Date applyDate;
    private int validateTime;

    public String getAccessTokenValue() {
        return accessTokenValue;
    }

    public void setAccessTokenValue(String accessTokenValue) {
        this.accessTokenValue = accessTokenValue;
    }

    public Date getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(Date applyDate) {
        this.applyDate = applyDate;
    }

    public int getValidateTime() {
        return validateTime;
    }

    public void setValidateTime(int validateTime) {
        this.validateTime = validateTime;
    }

    
}
