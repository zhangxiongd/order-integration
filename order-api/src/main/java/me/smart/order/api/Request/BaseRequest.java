package me.smart.order.api.Request;

import me.smart.order.enums.ResultCode;
import me.smart.order.exception.BusinessException;
import org.apache.commons.lang.StringUtils;

/**
 * Created by zhangxiong on 15/12/27.
 */
public abstract class BaseRequest implements ValidateRequest {

    protected Integer LENGTH_10 = 10;

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

    public void assertNotNull(String candidate, String candidateName, Integer length) throws BusinessException {
        if (StringUtils.isBlank(candidate) || (length != null && candidate.length() > length)) {
            String exceptionMessage = String.format("%s为空或无效", candidateName);
            throw new BusinessException(ResultCode.REQUEST_NOT_VALID, exceptionMessage);
        }

    }
}
