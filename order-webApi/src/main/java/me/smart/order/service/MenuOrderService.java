package me.smart.order.service;

import me.smart.order.api.Request.MenuOrderRequest;
import me.smart.order.exception.BusinessException;
import me.smart.order.model.MenuOrder;

/**
 * Created by zhangxiong on 16/2/14.
 */
public interface MenuOrderService {
    public MenuOrder transMenuOrder(MenuOrderRequest menuOrderRequest) throws BusinessException;
}
