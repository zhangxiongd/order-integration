package me.smart.order.service;

import me.smart.order.api.Result;
import me.smart.order.api.member.Request.CancelRequest;
import me.smart.order.api.member.Request.MenuOrderRequest;
import me.smart.order.api.member.Response.PayResult;
import me.smart.order.exception.BusinessException;

/**
 * Created by zhangxiong on 16/2/14.
 */
public interface MenuOrderService {
    Result<PayResult> transMenuOrder(MenuOrderRequest menuOrderRequest) throws BusinessException;

    Result cancelMenuOrder(CancelRequest request) throws Exception;

}
