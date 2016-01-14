package me.smart.order.api.Request;

import me.smart.order.exception.BusinessException;

/**
 * Created by zhangxiong on 15/12/27.
 */
public abstract class BaseRequest implements ValidateRequest {

    protected String version;

    protected String sign;

    protected String signMethod;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getSignMethod() {
        return signMethod;
    }

    public void setSignMethod(String signMethod) {
        this.signMethod = signMethod;
    }

    public abstract void validate() throws BusinessException;
}
