package me.smart.order.api.merchant.request;

import lombok.ToString;
import me.smart.order.exception.BusinessException;

/**
 * Created by zhangxiong on 16/4/3.
 */
@ToString
public class LoginRequest extends BaseRequest {

    private String mobile;
    private String password;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public void validate() throws BusinessException {
        super.validate(source, token);
        assertNotNull(mobile, "手机号", LENGTH_11);
        assertNotNull(password, "密码", null);
    }
}
