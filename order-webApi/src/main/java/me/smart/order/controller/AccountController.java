package me.smart.order.controller;

import me.smart.order.api.Result;
import me.smart.order.api.member.Request.SendVerifyCodeRequest;
import me.smart.order.api.member.Request.ValidateVerifyCodeRequest;
import me.smart.order.api.member.Response.SendVerifyCodeResponse;
import me.smart.order.api.member.Response.ValidateVerifyCodeResponse;
import me.smart.order.service.VerifyCodeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by zhangxiong on 16/3/30.
 */
@Controller
public class AccountController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(AccountController.class);

    @Resource
    private VerifyCodeService verifyCodeService;

    @RequestMapping(value = "/verifyCode/send")
    @ResponseBody
    public Result<SendVerifyCodeResponse> sendVerifyCode(@RequestBody SendVerifyCodeRequest sendVerifyCodeRequest) {
        try {
            sendVerifyCodeRequest.validate();
            verifyCodeService.sendVerifyCode(sendVerifyCodeRequest);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return errorResult(e);
        }
        return Result.createResult(new SendVerifyCodeResponse(sendVerifyCodeRequest.getMobile()));
    }

    @RequestMapping(value = "/verifyCode/validate")
    @ResponseBody
    public Result<ValidateVerifyCodeResponse> validateVerifyCode(@RequestBody ValidateVerifyCodeRequest validateVerifyCodeRequest) {
        try {
            validateVerifyCodeRequest.validate();
            return verifyCodeService.validateVerifyCode(validateVerifyCodeRequest);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return errorResult(e);
        }
    }
}
