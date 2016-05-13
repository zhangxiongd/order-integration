package me.smart.order.controller;

import me.smart.order.api.MenuOrderCourseInfo;
import me.smart.order.api.Result;
import me.smart.order.api.member.Request.MenuOrderRequest;
import me.smart.order.enums.ResultCode;
import me.smart.order.exception.BusinessException;
import me.smart.order.exception.ResultCodeException;
import me.smart.order.util.ConvertUtil;
import me.smart.order.util.SignUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by zhangxiong on 16/1/10.
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
        if (!SignUtil.checkSign(map, "")) {
            logger.error("Sign error cause of requestSign is not valid");
            throw new BusinessException(ResultCode.SIGN_ERROR, "签名错误");
        }

    }


    public static void main(String[] args) {
        MenuOrderRequest menuOrderRequest = new MenuOrderRequest();
        menuOrderRequest.setMemberId("12");
        menuOrderRequest.setMerchantId("11811");
        menuOrderRequest.setTableNo("9");
        menuOrderRequest.setTotalAmount(2000);
        List<MenuOrderCourseInfo> menuOrderCourseInfos = new ArrayList<MenuOrderCourseInfo>();
        MenuOrderCourseInfo info1 = new MenuOrderCourseInfo();
        info1.setCount(1);
        info1.setCourseId(123l);
        info1.setPrice(1200);

        MenuOrderCourseInfo info2 = new MenuOrderCourseInfo();
        info2.setCount(1);
        info2.setCourseId(124l);
        info2.setPrice(1200);
        menuOrderCourseInfos.add(info1);
        menuOrderCourseInfos.add(info2);
        menuOrderRequest.setMenuOrderCourseInfoList(menuOrderCourseInfos);
        Map<String, Object> map = ConvertUtil.objectToMap(menuOrderRequest);
        System.out.println(map);
        System.out.println(SignUtil.signMD5(map, "rrwer23423f"));

    }
}
