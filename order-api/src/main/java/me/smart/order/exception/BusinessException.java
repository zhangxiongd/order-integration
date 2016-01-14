package me.smart.order.exception;

import me.smart.order.enums.ResultCode;

/**
 * Created by zhangxiong on 15/12/27.
 */
public class BusinessException extends Exception implements ResultCodeException {

    /**
     * 序列码
     */
    private static final long serialVersionUID = 3595349276772863635L;

    /**
     * 错误码
     */
    private ResultCode code;

    public BusinessException(ResultCode code) {
        super(code.getDesc());
        this.code = code;
    }

    /**
     * @param code    错误码
     * @param message 错误描述
     */
    public BusinessException(ResultCode code, String message) {
        super(message);
        this.code = code;
    }

    /**
     * 返回错误码
     *
     * @return code 错误码
     */
    public ResultCode getCode() {
        return code;
    }
}
