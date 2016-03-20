package me.smart.order.dao;

import me.smart.order.model.PaymentRecord;

import java.util.Map;

/**
 * Created by zhangxiong on 15/12/31.
 */
public interface PaymentRecordMapper {

    /**
     * 根据merchant_id  member_id out_trade_no payment_way trade_type
     * 查找相应的支付流水
     *
     * @param map
     * @return
     */
    PaymentRecord selectByMidAndNoAndPayType(Map<String, Object> map);

}
