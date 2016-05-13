package me.smart.order.api.merchant.request;

import lombok.ToString;
import me.smart.order.exception.BusinessException;

/**
 * Created by zhangxiong on 16/4/1.
 */
@ToString
public class RegisterRequest extends BaseRequest {

    private String mobile;

    private String loginPassword;

    private String merchantName;

    private String realName;

    private String address;

    private String merchantDesc;

    private String email;


    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getLoginPassword() {
        return loginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMerchantDesc() {
        return merchantDesc;
    }

    public void setMerchantDesc(String merchantDesc) {
        this.merchantDesc = merchantDesc;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public void validate() throws BusinessException {
        super.validate(source, token);
        assertNotNull(sign, "签名", null);
        assertNotNull(mobile, "手机号", LENGTH_11);
        assertNotNull(loginPassword, "登录密码", LENGTH_64);
        assertNotNull(merchantName, "店名", 20);
        assertNotNull(realName, "真名", 20);
        assertNotNull(address, "地址", 100);
        assertNotNull(merchantDesc, "介绍", 100);

    }
}
