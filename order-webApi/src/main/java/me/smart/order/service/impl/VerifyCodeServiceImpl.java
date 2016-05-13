package me.smart.order.service.impl;

import me.smart.order.api.Result;
import me.smart.order.api.member.Request.SendVerifyCodeRequest;
import me.smart.order.api.member.Request.ValidateVerifyCodeRequest;
import me.smart.order.api.member.Response.ValidateVerifyCodeResponse;
import me.smart.order.constant.AliMessageConstant;
import me.smart.order.constant.RedisCacheConstant;
import me.smart.order.dao.VerifyCodeMapper;
import me.smart.order.enums.AccountOperationType;
import me.smart.order.enums.ErrorCode;
import me.smart.order.enums.TokenType;
import me.smart.order.exception.BusinessException;
import me.smart.order.exception.SystemException;
import me.smart.order.model.VerifyCode;
import me.smart.order.redis.RedisService;
import me.smart.order.service.VerifyCodeService;
import me.smart.order.util.PasswordUtil;
import me.smart.order.util.RandomStringGenerator;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import static me.smart.order.enums.ResultCode.*;

/**
 * Created by zhangxiong on 16/3/30.
 */
@Service("verifyCodeService")
public class VerifyCodeServiceImpl implements VerifyCodeService {

    private Logger logger = LoggerFactory.getLogger(VerifyCodeServiceImpl.class);

    @Resource
    private VerifyCodeMapper verifyCodeMapper;

    @Value("verify_code_dead_line")
    private int verifyCodeDeadLine;

    @Resource
    private RedisService redisService;


    @Override
    public void sendVerifyCode(SendVerifyCodeRequest sendVerifyCodeRequest) throws BusinessException {
        String code = RandomStringGenerator.getVerifyCode();

        //先落地验证码成功再调第三方发短信，如果第三方发送失败，则更新这条记录失败
        VerifyCode verifyCode = new VerifyCode(sendVerifyCodeRequest.getMobile(), code, verifyCodeDeadLine);
        verifyCodeMapper.insert(verifyCode);
        //调用第三方发短信模块
        boolean result = AliMessageConstant.sendMessage(sendVerifyCodeRequest.getMobile(), code);
        if (!result) {
            //发送短信失败，将此验证码记录置失效
            verifyCodeMapper.updateForIsDelete(sendVerifyCodeRequest.getMobile(), code);
            throw new BusinessException(VERIFY_CODE_SEND_ERROR);
        }
    }

    @Override
    public Result<ValidateVerifyCodeResponse> validateVerifyCode(ValidateVerifyCodeRequest request) throws BusinessException {
        //首先校验验证码
        VerifyCode verifyCode = verifyCodeMapper.selectByMobileAndVerifyCode(request.getMobile(), request.getVerifyCode());
        if (verifyCode == null) {
            throw new BusinessException(VERIFY_CODE_VALIDATE_ERROR);
        }
        //校验是否超时 todo 将超时判断放数据库 或者所有时间都是系统时间获取
        if (new DateTime(verifyCode.getCreatedAt()).plusSeconds(verifyCode.getDeadLine()).getMillis() > System.currentTimeMillis()) {
            throw new BusinessException(VERIFY_CODE_TIMEOUT_ERROR);
        }

        //生成token
        try {
            String salt = createSalt(request.getMobile(), AccountOperationType.parse(request.getOperationType()));
            String dynamicToken = createToken(request.getMobile(), AccountOperationType.parse(request.getOperationType()));
            String token = salt + dynamicToken;
            logger.info("校验验证码生成的token,token={}", token);
            ValidateVerifyCodeResponse response = new ValidateVerifyCodeResponse();
            response.setMobile(request.getMobile());
            response.setToken(token);
            return Result.createResult(response);
        } catch (Exception e) {
            throw new BusinessException(SYSTEM_ERROR);
        }
    }


    /**
     * 生成密码salt，缓存redis里，在注册或重置密码的时候 保存表里
     */
    private String createSalt(String mobile, AccountOperationType operationType) throws Exception {
        String salt = PasswordUtil.createSalt();
        String lable = null;
        if (AccountOperationType.REGISTER_ACCOUNT.getCode() == operationType.getCode()) {
            lable = TokenType.REGISTER_SALT.getLable();
        } else if (AccountOperationType.MODIFY_PWD.getCode() == operationType.getCode()) {
            lable = TokenType.FORGET_PWD_SALT.getLable();
        } else {
            throw new BusinessException(OPERATION_VALID);
        }
        String key = redisService.getKey(mobile, lable);
        boolean success = redisService.put(key, salt, RedisCacheConstant.REDIS_THIRTY_MIN_TIME_OUT);
        if (!success) {
            logger.info("createSalt addStrToRedis error key={}", key);
            throw new SystemException(ErrorCode.REDIS_ERROR, "createSalt 存储缓存失败!");
        }
        logger.info("createSalt addStrToRedis success key={} value={}", key, salt);
        return salt;
    }

    /**
     * 生成token
     */
    private String createToken(String mobile, AccountOperationType accountOperationType) throws BusinessException {
        String dynamicToken = PasswordUtil.createSalt();
        String lable = getTokenLable(accountOperationType);
        String key = redisService.getKey(mobile, lable);
        boolean success = redisService.put(key, dynamicToken, RedisCacheConstant.REDIS_THIRTY_MIN_TIME_OUT);
        if (!success) {
            logger.info("createSalt addStrToRedis error key={}", key);
            throw new SystemException(ErrorCode.REDIS_ERROR, "createSalt 存储缓存失败!");
        }
        logger.info("createSalt addStrToRedis success key={} value={}", key, lable);
        return dynamicToken;
    }


    private String getTokenLable(AccountOperationType accountOperationType) throws BusinessException {
        String lable = null;
        if (AccountOperationType.REGISTER_ACCOUNT.getCode() == accountOperationType.getCode()) {
            lable = TokenType.REGISTER_TOKEN.getLable();
        } else if (AccountOperationType.FORGET_PWD.getCode() == accountOperationType.getCode()) {
            lable = TokenType.FORGET_PWD_TOKEN.getLable();
        } else {
            throw new BusinessException(OPERATION_VALID);
        }
        return lable;
    }

}
