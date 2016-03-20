package me.smart.order.exception;

import me.smart.order.enums.ErrorCode;

/**
 * Created by zhangxiong on 16/3/13.
 */
public class SystemException extends RuntimeException {
    private ErrorCode errorCode;

    private String message;

    public SystemException() {
    }

    public SystemException(ErrorCode errorCode) {
        super();
        this.errorCode = errorCode;
    }

    public SystemException(ErrorCode errorCode, String message) {
        super();
        this.message = message;
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * 返回错误码
     *
     * @return code 错误码
     */
    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }
}
