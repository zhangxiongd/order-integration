package me.smart.order.util;

import java.util.Date;

/**
 * Created by zhangxiong on 16/5/15.
 */
public class OrderNoUtils {

    public static String getOrderNo(String memberId) {
        return DateUtil.date2timeString(new Date()) + memberId;
    }

}
