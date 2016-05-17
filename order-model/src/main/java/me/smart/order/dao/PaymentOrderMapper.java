package me.smart.order.dao;

import me.smart.order.model.PaymentOrder;
import org.apache.ibatis.annotations.Param;

/**
 * Created by zhangxiong on 15/12/31.
 */
public interface PaymentOrderMapper {
    int insert(PaymentOrder paymentOrder);

    PaymentOrder selectByMenuOrderNo(@Param("memberId") Long memberId, @Param("menuOrderNo") String menuOrderNo);
}
