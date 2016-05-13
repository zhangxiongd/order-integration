package me.smart.order.api.member.Response;

/**
 * Created by zhangxiong on 16/3/31.
 */
public class ValidateVerifyCodeResponse {
    private String mobile;
    //salt+dynamic toke 128位
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
