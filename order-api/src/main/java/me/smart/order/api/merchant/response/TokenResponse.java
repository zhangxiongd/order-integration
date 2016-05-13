package me.smart.order.api.merchant.response;

import lombok.ToString;

/**
 * Created by zhangxiong on 16/4/3.
 */
@ToString
public class TokenResponse {
    private String mobile;
    //salt+dynamicToke
    private String token;

    private String salt;


    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
}
