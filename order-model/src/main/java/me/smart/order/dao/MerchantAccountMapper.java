package me.smart.order.dao;

import me.smart.order.model.MerchantAccount;
import org.apache.ibatis.annotations.Param;

/**
 * Created by zhangxiong on 16/4/3.
 */
public interface MerchantAccountMapper {
    int insert(MerchantAccount merchantAccount);

    MerchantAccount findByMobile(@Param("mobile") String mobile);

    /**
     * 修改登录密码或者密码
     *
     * @param account
     * @return
     */
    int updateAccount(MerchantAccount account);
}
