package me.smart.order.api.merchant.request;

import me.smart.order.enums.ResultCode;
import me.smart.order.exception.BusinessException;
import org.apache.commons.lang.StringUtils;

/**
 * Created by zhangxiong on 16/4/1.
 */
public abstract class BaseRequest implements ValidateRequest {
    protected Integer LENGTH_10 = 10;
    protected Integer LENGTH_11 = 11;
    protected Integer LENGTH_20 = 20;
    protected Integer LENGTH_64 = 64;


    /**
     * 接口版本号
     */
    protected String version;

    /**
     * 请求来源
     * 1:android
     * 2:ios
     */
    protected Integer source;

    /**
     * 手机唯一识别号
     */
    protected String token;
    /**
     * 签名
     */
    protected String sign;
    /**
     * 签名方式
     */
    protected String signMethod;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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

    public void validate(Object... object) throws BusinessException {
        for (Object o : object) {
            if (object == null) {
                throw new BusinessException(ResultCode.VALIDATE_PARAM_ERROR);
            }
        }
    }

    public void validateCommon() throws BusinessException {
        if (source == null || StringUtils.isBlank(sign) || StringUtils.isBlank(token)) {
            throw new BusinessException(ResultCode.VALIDATE_PARAM_ERROR);
        }
    }


    public void assertNotNull(String candidate, String candidateName, Integer length) throws BusinessException {

        if (StringUtils.isBlank(candidate) || (length != null && candidate.length() > length)) {
            String exceptionMessage = String.format("%s为空或无效", candidateName);
            throw new BusinessException(ResultCode.REQUEST_NOT_VALID, exceptionMessage);
        }

    }
}
