package me.smart.order.api.member.Request;

import me.smart.order.exception.BusinessException;

/**
 * Created by zhangxiong on 16/3/28.
 */
public class CancelRequest extends BaseRequest {

    //商家id
    private String merchantId;
    //用户ID
    private String memberId;
    //支付订单号
    private String outTradeNo;


    @Override
    public void validate() throws BusinessException {

    }
}
