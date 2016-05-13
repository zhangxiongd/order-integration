package me.smart.order.dao;

import me.smart.order.model.VerifyCode;
import org.apache.ibatis.annotations.Param;

/**
 * Created by zhangxiong on 16/3/31.
 */
public interface VerifyCodeMapper {
    int insert(VerifyCode verifyCode);

    VerifyCode selectByMobileAndVerifyCode(@Param("mobile") String mobile, @Param("verifyCode") String verifyCode);

    int updateForIsValidated(@Param("mobile") String mobile, @Param("verifyCode") String verifyCode);

    int updateForIsDelete(@Param("mobile") String mobile, @Param("verifyCode") String verifyCode);
}
