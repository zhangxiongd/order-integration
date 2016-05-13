package me.smart.order.api.member.Request;

import me.smart.order.enums.ResultCode;
import me.smart.order.exception.BusinessException;

/**
 * Created by zhangxiong on 16/3/31.
 */
public class ValidateVerifyCodeRequest extends BaseRequest {

    private String mobile;
    private String verifyCode;
    private Integer operationType;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    public Integer getOperationType() {
        return operationType;
    }

    public void setOperationType(Integer operationType) {
        this.operationType = operationType;
    }

    @Override
    public void validate() throws BusinessException {
        if (operationType == null) {
            throw new BusinessException(ResultCode.REQUEST_NOT_VALID);
        }
        assertNotNull(mobile, "手机号", null);
        assertNotNull(verifyCode, "验证码", 6);
    }
}
