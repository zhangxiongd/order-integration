package me.smart.order.service;

import me.smart.order.api.PaymentInfo;
import me.smart.order.api.Result;
import me.smart.order.enums.PaymentWay;
import me.smart.order.enums.TradeType;
import me.smart.order.exception.BusinessException;
import me.smart.order.model.MenuOrder;
import me.smart.order.model.PaymentOrder;
import me.smart.order.model.PaymentRecord;

/**
 * Created by zhangxiong on 16/1/14.
 */
public interface PaymentService {

    public PaymentOrder placeOrder(MenuOrder menuOrder) throws BusinessException;

    public Result transactByOrder(PaymentOrder paymentOrder) throws BusinessException;

    public void updatePaymentRecordSuccess(PaymentRecord PaymentRecord);

    public void updatePaymentRecordNotPay(PaymentRecord PaymentRecord);

    public PaymentRecord selectByMidAndNoAndPayType(PaymentOrder paymentOrder, PaymentWay paymentWay, TradeType tradeType);

    public PaymentRecord insertPaymentRecord(PaymentOrder paymentOrder, PaymentInfo paymentInfo);


}
