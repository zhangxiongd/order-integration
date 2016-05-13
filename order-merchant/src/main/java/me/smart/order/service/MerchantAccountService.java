package me.smart.order.service;

import me.smart.order.api.Result;
import me.smart.order.api.merchant.request.*;
import me.smart.order.api.merchant.response.*;

/**
 * Created by zhangxiong on 16/4/1.
 */
public interface MerchantAccountService {
    Result<RegisterResponse> register(RegisterRequest request) throws Exception;

    Result<LoginResponse> login(LoginRequest request) throws Exception;

    Result<TokenResponse> token(TokenRequest request) throws Exception;

    Result<ModifyResponse> modify(ModifyRequest request) throws Exception;

    Result<ForgetPasswordResponse> forgetPassword(ForgetPasswordRequest request) throws Exception;
}
