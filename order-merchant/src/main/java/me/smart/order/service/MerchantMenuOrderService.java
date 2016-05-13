package me.smart.order.service;

import me.smart.order.api.Result;
import me.smart.order.api.merchant.request.MenuOrderDealRequest;
import me.smart.order.api.merchant.request.MenuOrderListRequest;
import me.smart.order.api.merchant.response.MenuOrderListResponse;

/**
 * Created by zhangxiong on 16/4/4.
 */
public interface MerchantMenuOrderService {
    Result<MenuOrderListResponse> list(MenuOrderListRequest request) throws Exception;

    Result deal(MenuOrderDealRequest request) throws Exception;
}
