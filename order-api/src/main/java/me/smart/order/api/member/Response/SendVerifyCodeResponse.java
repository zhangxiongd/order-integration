package me.smart.order.api.member.Response;

/**
 * Created by zhangxiong on 16/3/30.
 */
public class SendVerifyCodeResponse {
    private String mobile;

    public SendVerifyCodeResponse(String mobile) {
        this.mobile = mobile;
    }

    public SendVerifyCodeResponse() {
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
