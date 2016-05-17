package me.smart.order.service.impl;

import me.smart.order.api.Result;
import me.smart.order.api.merchant.request.ForgetPasswordRequest;
import me.smart.order.api.merchant.request.LoginRequest;
import me.smart.order.api.merchant.request.ModifyRequest;
import me.smart.order.api.merchant.request.RegisterRequest;
import me.smart.order.api.merchant.request.TokenRequest;
import me.smart.order.api.merchant.response.ForgetPasswordResponse;
import me.smart.order.api.merchant.response.LoginResponse;
import me.smart.order.api.merchant.response.ModifyResponse;
import me.smart.order.api.merchant.response.RegisterResponse;
import me.smart.order.api.merchant.response.TokenResponse;
import me.smart.order.constant.CommonConstant;
import me.smart.order.constant.RedisCacheConstant;
import me.smart.order.dao.MerchantAccountMapper;
import me.smart.order.dao.MerchantMapper;
import me.smart.order.enums.AccountOperationType;
import me.smart.order.enums.ErrorCode;
import me.smart.order.enums.ResultCode;
import me.smart.order.enums.TokenType;
import me.smart.order.exception.BusinessException;
import me.smart.order.exception.SystemException;
import me.smart.order.model.Merchant;
import me.smart.order.model.MerchantAccount;
import me.smart.order.redis.RedisService;
import me.smart.order.service.MerchantAccountService;
import me.smart.order.util.JsonConvertUtils;
import me.smart.order.util.PasswordUtil;
import me.smart.order.util.SignUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;

import static me.smart.order.enums.ResultCode.MERCHANT_ACCOUNT_EXISTED_ERROR;
import static me.smart.order.enums.ResultCode.MERCHANT_ACCOUNT_NOT_EXIST_ERROR;
import static me.smart.order.enums.ResultCode.MERCHANT_ACCOUNT_PASSWORD_ERROR;
import static me.smart.order.enums.ResultCode.MERCHANT_NOT_EXIST_ERROR;
import static me.smart.order.enums.ResultCode.OPERATION_VALID;
import static me.smart.order.enums.ResultCode.REDIS_KEY_ERROR;
import static me.smart.order.enums.ResultCode.SQL_ERROR;
import static me.smart.order.enums.ResultCode.SYSTEM_ERROR;

/**
 * Created by zhangxiong on 16/4/1.
 */
@Service("merchantAccountService")
public class MerchantAccountServiceImpl implements MerchantAccountService {
    private Logger logger = LoggerFactory.getLogger(MerchantAccountServiceImpl.class);

    @Resource
    private MerchantMapper merchantMapper;
    @Resource
    private RedisService redisService;
    @Resource
    private MerchantAccountMapper merchantAccountMapper;

    @Override
    @Transactional
    public Result<RegisterResponse> register(RegisterRequest request) throws Exception {
        request.validate();
        Map<String, Object> signMap = JsonConvertUtils.objectToMap(request);
        //从redis获取authKey
        String key = redisService.getKey(request.getMobile(), TokenType.REGISTER_TOKEN.getLable());
        String authKey = redisService.get(key);
        //验签
        SignUtil.checkSign(signMap, authKey);
        MerchantAccount account = merchantAccountMapper.findByMobile(request.getMobile());
        if (account != null) {
            logger.error("商户帐号已经存在, mobile={}, accountId={},merchantId={}", request.getMobile(),
                    account.getId(), account.getMerchantId());
            throw new BusinessException(MERCHANT_ACCOUNT_EXISTED_ERROR);
        }
        Merchant merchant = createMerchant(request);
        merchantMapper.insert(merchant);
        MerchantAccount merchantAccount = createMerchantAccount(merchant.getId(), request);
        merchantAccountMapper.insert(merchantAccount);
        RegisterResponse response = new RegisterResponse(merchant.getId().toString(),
                merchantAccount.getId().toString());
        return Result.createResult(response);
    }


    @Override
    public Result<LoginResponse> login(LoginRequest request) throws Exception {
        request.validate();
        Map<String, Object> signMap = JsonConvertUtils.objectToMap(request);
        //获取加密key 然后再验签
        String key = redisService.getKey(request.getMobile(), TokenType.LOGIN_TOKEN.getLable());
        String authKey = redisService.get(key);
        SignUtil.checkSign(signMap, authKey);

        MerchantAccount account = merchantAccountMapper.findByMobile(request.getMobile());
        if (account == null) {
            logger.error("未找到商户帐号, mobile={}", request.getMobile());
            throw new BusinessException(MERCHANT_ACCOUNT_NOT_EXIST_ERROR);
        }
        if (!account.getLoginPassword().equals(request.getPassword())) {
            logger.error("密码错误,accountId={},requestPwd ={}", account.getId(), request.getPassword());
            throw new BusinessException(MERCHANT_ACCOUNT_PASSWORD_ERROR);
        }
        Merchant merchant = merchantMapper.getMerchantById(account.getMerchantId());
        if (merchant == null) {
            throw new BusinessException(MERCHANT_NOT_EXIST_ERROR);
        }
        if (!account.getToken().equals(request.getToken())) {
            //如果token不一样 则更新token和source
            account.setToken(request.getToken());
            account.setSource(request.getSource());
            account.setLastLoginAt(new Date());
            try {
                merchantAccountMapper.updateAccount(account);
            } catch (Exception e) {
                logger.error("更新account帐户失败,mobile={},errorMsg={}", request.getMobile(), e.getMessage(), e);
                throw new BusinessException(SQL_ERROR);
            }
        }
        LoginResponse response = new LoginResponse();
        response.setMobile(request.getMobile());
        response.setAccountId(account.getId().toString());
        response.setMerchantId(merchant.getId().toString());
        response.setRealName(account.getRealName());
        response.setMerchantName(merchant.getMerchantName());
        return Result.createResult(response);
    }


    @Override
    public Result<TokenResponse> token(TokenRequest request) throws Exception {
        request.validate();
        Map<String, Object> signMap = JsonConvertUtils.objectToMap(request);
        //获取加密key 然后再验签
        String authKey = CommonConstant.getAuthKey(request.getSource());
        SignUtil.checkSign(signMap, authKey);

        MerchantAccount account = merchantAccountMapper.findByMobile(request.getMobile());
        if (account == null) {
            logger.error("未找到商户帐号, mobile={}", request.getMobile());
            throw new BusinessException(MERCHANT_ACCOUNT_NOT_EXIST_ERROR);
        }
        String token = createToken(request.getMobile(), AccountOperationType.parse(request.getOperationType()));
        TokenResponse response = new TokenResponse();
        response.setMobile(request.getMobile());
        response.setToken(account.getLoginSalt() + token);
        //如果是修改密码 则另外返回新的salt
        Result result = Result.createResult(response);
        if (AccountOperationType.MODIFY_PWD.getCode() == request.getOperationType()) {
            try {
                String salt = createSalt(request.getMobile());
                response.setSalt(salt);
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
                throw new BusinessException(ResultCode.SYSTEM_ERROR);
            }
        }
        logger.info("token response ={}", response);
        return result;
    }


    @Override
    public Result<ModifyResponse> modify(ModifyRequest request) throws Exception {
        request.validate();
        //验签
        Map<String, Object> signMap = JsonConvertUtils.objectToMap(request);
        //获取加密key 然后再验签
        String key = redisService.getKey(request.getMobile(), TokenType.MODIFY_PWD_TOKEN.getLable());
        String authKey = redisService.get(key);
        SignUtil.checkSign(signMap, authKey);

        //获取帐号
        MerchantAccount account = merchantAccountMapper.findByMobile(request.getMobile());
        if (account == null) {
            logger.error("未找到商户帐号, mobile={}", request.getMobile());
            throw new BusinessException(MERCHANT_ACCOUNT_NOT_EXIST_ERROR);
        }
        //校验原密码
        if (!account.getLoginPassword().equals(request.getOldPassword())) {
            throw new BusinessException(MERCHANT_ACCOUNT_PASSWORD_ERROR);
        }
        //修改密码
        account.setLoginPassword(request.getNewPassword());
        //获取新的salt
        String redisKey = redisService.getKey(request.getMobile(), TokenType.MODIFY_PWD_SALT.getLable());
        String newSalt = redisService.get(redisKey);
        if (StringUtils.isBlank(newSalt)) {
            throw new BusinessException(REDIS_KEY_ERROR);
        }
        account.setLoginSalt(newSalt);
        account.setToken(request.getToken());
        account.setSource(request.getSource());
        try {
            merchantAccountMapper.updateAccount(account);
        } catch (Exception e) {
            logger.error("更新account帐户失败,mobile={},errorMsg={}", request.getMobile(), e.getMessage(), e);
            throw new BusinessException(SQL_ERROR);
        }
        Merchant merchant = merchantMapper.getMerchantById(account.getMerchantId());
        if (merchant == null) {
            throw new BusinessException(MERCHANT_NOT_EXIST_ERROR);
        }

        return Result.createResult(createModifyResponse(merchant, account));
    }


    @Override
    public Result<ForgetPasswordResponse> forgetPassword(ForgetPasswordRequest request) throws Exception {
        request.validate();
        Map<String, Object> signMap = JsonConvertUtils.objectToMap(request);
        //获取加密key 然后再验签
        String key = redisService.getKey(request.getMobile(), TokenType.FORGET_PWD_TOKEN.getLable());
        String authKey = redisService.get(key);
        SignUtil.checkSign(signMap, authKey);

        MerchantAccount account = merchantAccountMapper.findByMobile(request.getMobile());
        if (account == null) {
            logger.error("未找到商户帐号, mobile={}", request.getMobile());
            throw new BusinessException(MERCHANT_ACCOUNT_NOT_EXIST_ERROR);
        }
        account.setLoginPassword(request.getPassword());
        String saltKey = redisService.getKey(request.getMobile(), TokenType.FORGET_PWD_SALT.getLable());
        String salt = redisService.get(saltKey);
        if (StringUtils.isBlank(salt)) {
            throw new BusinessException(REDIS_KEY_ERROR);
        }
        //更新salt
        account.setLoginSalt(salt);
        account.setToken(request.getToken());
        account.setSource(request.getSource());
        try {
            merchantAccountMapper.updateAccount(account);
        } catch (Exception e) {
            logger.error("更新account帐户失败,mobile={},errorMsg={}", request.getMobile(), e.getMessage(), e);
            throw new BusinessException(SQL_ERROR);
        }
        ForgetPasswordResponse response = new ForgetPasswordResponse();
        response.setMobile(request.getMobile());
        return Result.createResult(response);
    }

    private ModifyResponse createModifyResponse(Merchant merchant, MerchantAccount account) {
        ModifyResponse response = new ModifyResponse();
        response.setAccountId(account.getId().toString());
        response.setMobile(account.getMobile());
        response.setMerchantId(merchant.getId().toString());
        response.setMerchantName(merchant.getMerchantName());
        response.setRealName(account.getRealName());
        return response;
    }

    /**
     * 生成dynamicToken
     *
     * @param mobile
     * @param operationType
     * @return
     * @throws BusinessException
     */
    private String createToken(String mobile, AccountOperationType operationType) throws BusinessException {
        String tokenLable = null;
        if (AccountOperationType.LOGIN_ACCOUNT.getCode() == operationType.getCode()) {
            tokenLable = TokenType.LOGIN_TOKEN.getLable();
        } else if (AccountOperationType.MODIFY_PWD.getCode() == operationType.getCode()) {
            tokenLable = TokenType.MODIFY_PWD_TOKEN.getLable();
        } else {
            throw new BusinessException(OPERATION_VALID);
        }
        String key = redisService.getKey(mobile, tokenLable);
        String dynamicToken = PasswordUtil.createSalt();
        boolean isSuccess = redisService.put(key, dynamicToken, RedisCacheConstant.REDIS_THIRTY_MIN_TIME_OUT);
        if (!isSuccess) {
            logger.error("MerchantAccountServiceImpl createToken dynamicToken存redis失败");
            throw new BusinessException(SYSTEM_ERROR);
        }
        return dynamicToken;
    }


    //创建商户
    private Merchant createMerchant(RegisterRequest request) {
        Merchant merchant = new Merchant();
        merchant.setMerchantName(request.getMerchantName());
        merchant.setAddress(request.getAddress());
        merchant.setCreatedAt(new Date());
        merchant.setMerchantDesc(request.getMerchantDesc());
        merchant.setUpdatedAt(new Date());
        merchant.setIsDelete(false);
        return merchant;
    }


    //创建MerchantAccount
    private MerchantAccount createMerchantAccount(Long merchantId, RegisterRequest request) {
        String key = redisService.getKey(request.getMobile(), TokenType.REGISTER_TOKEN.getLable());
        String salt = redisService.get(key);
        MerchantAccount merchantAccount = new MerchantAccount(merchantId,
                request.getMobile(), request.getRealName(), request.getEmail(),
                request.getLoginPassword(), salt, true);
        return merchantAccount;
    }

    /**
     * 生成密码salt，缓存redis里，在注册的时候 保存表里
     */
    private String createSalt(String mobile) throws Exception {
        String salt = PasswordUtil.createSalt();
        String key = redisService.getKey(mobile, TokenType.MODIFY_PWD_SALT.getLable());
        boolean success = redisService.put(key, salt, RedisCacheConstant.REDIS_THIRTY_MIN_TIME_OUT);
        if (!success) {
            logger.info("createSalt addStrToRedis error key={}", key);
            throw new SystemException(ErrorCode.REDIS_ERROR, "createSalt 存储缓存失败!");
        }
        logger.info("createSalt addStrToRedis success key={} value={}", key, salt);
        return salt;
    }

}
