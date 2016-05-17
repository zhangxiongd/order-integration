package me.smart.order.controller;

import me.smart.order.api.Result;
import me.smart.order.api.merchant.request.ForgetPasswordRequest;
import me.smart.order.api.merchant.request.LoginRequest;
import me.smart.order.api.merchant.request.ModifyRequest;
import me.smart.order.api.merchant.request.RegisterRequest;
import me.smart.order.api.merchant.request.TokenRequest;
import me.smart.order.service.MerchantAccountService;
import me.smart.order.util.JsonConvertUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by zhangxiong on 16/4/1.
 */
@Controller
@RequestMapping("/account")
public class MerchantAccountController extends BaseController {
    Logger logger = LoggerFactory.getLogger(MerchantAccountController.class);

    @Resource
    private MerchantAccountService merchantAccountService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public Result register(@RequestBody String msg) {
        logger.info("MerchantAccountController register param={}", msg);
        try {
            RegisterRequest request = JsonConvertUtils.toObject(msg, RegisterRequest.class);
            return merchantAccountService.register(request);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return errorResult(e);
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Result login(@RequestBody String msg) {

        logger.info("MerchantAccountController login param={}", msg);
        try {
            LoginRequest request = JsonConvertUtils.toObject(msg, LoginRequest.class);
            return merchantAccountService.login(request);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return errorResult(e);
        }
    }

    /**
     * 登录或者修改密码获取token
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/token", method = RequestMethod.POST)
    @ResponseBody
    public Result token(@RequestBody String msg) {
        logger.info("MerchantAccountController token param={}", msg);
        try {
            TokenRequest request = JsonConvertUtils.toObject(msg, TokenRequest.class);
            return merchantAccountService.token(request);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return errorResult(e);
        }
    }


    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    @ResponseBody
    public Result modify(@RequestBody String msg) {
        logger.info("merchantAccountService modify param={}", msg);
        try {
            ModifyRequest request = JsonConvertUtils.toObject(msg, ModifyRequest.class);
            return merchantAccountService.modify(request);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return errorResult(e);
        }
    }


    @RequestMapping(value = "/reset", method = RequestMethod.POST)
    @ResponseBody
    public Result forgetPassWord(@RequestBody String msg) {
        logger.info("merchantAccountService modify param={}", msg);
        try {
            ForgetPasswordRequest request = JsonConvertUtils.toObject(msg, ForgetPasswordRequest.class);
            return merchantAccountService.forgetPassword(request);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return errorResult(e);
        }
    }
}
