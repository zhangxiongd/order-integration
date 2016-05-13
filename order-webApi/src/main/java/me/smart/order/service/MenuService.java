package me.smart.order.service;

import me.smart.order.api.member.Response.MerchantMenuResponse;
import me.smart.order.exception.BusinessException;

/**
 * Created by zhangxiong on 16/1/5.
 */
public interface MenuService {
    public MerchantMenuResponse getMerchantMenuList(Long merchantId) throws BusinessException;
}
