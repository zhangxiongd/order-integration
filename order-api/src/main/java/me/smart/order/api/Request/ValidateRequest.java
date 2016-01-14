package me.smart.order.api.Request;

import me.smart.order.exception.BusinessException;

/**
 * Created by zhangxiong on 15/12/27.
 */
public interface ValidateRequest {
    void validate() throws BusinessException;
}
