package me.smart.order.api.merchant.request;

import me.smart.order.exception.BusinessException;

/**
 * Created by zhangxiong on 16/4/3.
 */
public class ModifyRequest extends BaseRequest {

    private String mobile;
    private String oldPassword;
    private String newPassword;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    @Override
    public void validate() throws BusinessException {
        super.validate(source, sign, token);
        assertNotNull(mobile, "手机号", LENGTH_11);
        assertNotNull(oldPassword, "原密码", null);
        assertNotNull(newPassword, "新密码", null);
    }
}
