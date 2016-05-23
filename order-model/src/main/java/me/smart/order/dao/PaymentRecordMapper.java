package me.smart.order.dao;

import me.smart.order.model.PaymentRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;
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

    List<PaymentRecord> selectByMeIdAndNo(@Param("memberId") Long memberId, @Param("outTradeNo") String outTradeNo);

    PaymentRecord selectByTransactionId(@Param("transactionId") String transactionId);

    int update(PaymentRecord paymentRecord);

    int insert(PaymentRecord paymentRecord);
}
