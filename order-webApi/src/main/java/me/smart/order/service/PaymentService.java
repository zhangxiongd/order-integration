package me.smart.order.service;

import me.smart.order.api.PaymentInfo;
import me.smart.order.api.Result;
import me.smart.order.api.member.Request.CancelRequest;
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

    PaymentOrder placeOrder(MenuOrder menuOrder) throws BusinessException;

    Result transactByOrder(PaymentOrder paymentOrder) throws BusinessException;

    void updatePaymentRecordSuccess(PaymentRecord PaymentRecord);

    void updatePaymentRecordNotPay(PaymentRecord PaymentRecord);

    PaymentRecord selectByMidAndNoAndPayType(PaymentOrder paymentOrder, PaymentWay paymentWay, TradeType tradeType);

    PaymentRecord insertPaymentRecord(PaymentOrder paymentOrder, PaymentInfo paymentInfo);

    void cancel(CancelRequest cancelRequest) throws Exception;


}
