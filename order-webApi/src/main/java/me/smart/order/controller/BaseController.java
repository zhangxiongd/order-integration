package me.smart.order.controller;

import me.smart.order.api.Result;
import me.smart.order.enums.ResultCode;
import me.smart.order.exception.ResultCodeException;

/**
 * Created by zhangxiong on 16/1/10.
 */
public class BaseController {
    protected static Result errorResult(Exception ex) {
        if (ex instanceof ResultCodeException) {
            return new Result(((ResultCodeException) ex).getCode(), ex.getMessage());
        }

        return new Result(ResultCode.SYSTEM_ERROR, ResultCode.SYSTEM_ERROR.getDesc());
    }
}
