package me.smart.order.controller;

import me.smart.order.api.Result;
import me.smart.order.api.merchant.request.*;
import me.smart.order.service.MerchantAccountService;
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
    public Result register(@RequestBody RegisterRequest request) {
        logger.info("MerchantAccountController register param={}", request);
        try {
            return merchantAccountService.register(request);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return errorResult(e);
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Result login(@RequestBody LoginRequest request) {

        logger.info("MerchantAccountController login param={}", request);
        try {
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
    public Result token(@RequestBody TokenRequest request) {
        logger.info("MerchantAccountController token param={}", request);
        try {
            return merchantAccountService.token(request);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return errorResult(e);
        }
    }


    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    @ResponseBody
    public Result modify(@RequestBody ModifyRequest request) {
        logger.info("merchantAccountService modify param={}", request);
        try {
            return merchantAccountService.modify(request);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return errorResult(e);
        }
    }


    @RequestMapping(value = "/reset", method = RequestMethod.POST)
    @ResponseBody
    public Result forgetPassWord(@RequestBody ForgetPasswordRequest request) {
        logger.info("merchantAccountService modify param={}", request);
        try {
            return merchantAccountService.forgetPassword(request);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return errorResult(e);
        }
    }
}
