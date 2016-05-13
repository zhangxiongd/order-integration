package me.smart.order.controller;

import me.smart.order.api.Result;
import me.smart.order.constant.CommonConstant;
import me.smart.order.enums.ResultCode;
import me.smart.order.exception.BusinessException;
import me.smart.order.exception.ResultCodeException;
import me.smart.order.util.ConvertUtil;
import me.smart.order.util.SignUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Created by zhangxiong on 16/4/1.
 */
public class BaseController {
    private static Logger logger = LoggerFactory.getLogger(BaseController.class);

    protected static Result errorResult(Exception ex) {
        if (ex instanceof ResultCodeException) {
            return new Result(((ResultCodeException) ex).getCode(), ex.getMessage());
        }
        return new Result(ResultCode.SYSTEM_ERROR, ResultCode.SYSTEM_ERROR.getDesc());
    }

    protected static void checkSign(Object object) throws Exception {
        Map<String, Object> map = ConvertUtil.objectToMap(object);
        if (map == null) {
            logger.error("Sign error cause of map is null, and the object is = {}", object.toString());
            throw new BusinessException(ResultCode.SIGN_ERROR, "签名错误");
        }
        SignUtil.checkSign(map, CommonConstant.getAuthKey(Integer.valueOf(map.get("source").toString())));
    }

}
