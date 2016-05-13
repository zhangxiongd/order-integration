package me.smart.order.service;

import me.smart.order.api.Result;
import me.smart.order.api.member.Request.SendVerifyCodeRequest;
import me.smart.order.api.member.Request.ValidateVerifyCodeRequest;
import me.smart.order.api.member.Response.ValidateVerifyCodeResponse;
import me.smart.order.exception.BusinessException;

/**
 * Created by zhangxiong on 16/3/30.
 */
public interface VerifyCodeService {

    void sendVerifyCode(SendVerifyCodeRequest sendVerifyCodeRequest) throws BusinessException;

    Result<ValidateVerifyCodeResponse> validateVerifyCode(ValidateVerifyCodeRequest validateVerifyCodeRequest) throws BusinessException;

}
