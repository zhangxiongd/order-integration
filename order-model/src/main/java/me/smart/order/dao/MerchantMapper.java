package me.smart.order.dao;

import me.smart.order.model.Merchant;

/**
 * Created by zhangxiong on 15/12/16.
 */
public interface MerchantMapper {
    Merchant getMerchantById(Long id);

    Merchant insert(Merchant merchant);
}
