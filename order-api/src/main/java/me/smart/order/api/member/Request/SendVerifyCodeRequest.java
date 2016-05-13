package me.smart.order.api.member.Request;

import me.smart.order.exception.BusinessException;

/**
 * Created by zhangxiong on 16/3/30.
 */
public class SendVerifyCodeRequest extends BaseRequest {

    private String mobile;

    @Override
    public void validate() throws BusinessException {
        assertNotNull(mobile, "手机号", null);
    }


    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
