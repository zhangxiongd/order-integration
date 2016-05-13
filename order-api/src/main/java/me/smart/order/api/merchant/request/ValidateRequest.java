package me.smart.order.api.merchant.request;

import me.smart.order.exception.BusinessException;

/**
 * Created by zhangxiong on 16/4/1.
 */
public interface ValidateRequest {
    void validate() throws BusinessException;
}
