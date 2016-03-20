package me.smart.order.util;

import java.net.InetAddress;

/**
 * Created by zhangxiong on 16/3/19.
 */
public class NetUtil {
    public static String getIp() {
        try {
            InetAddress addr = InetAddress.getLocalHost();
            return addr.getHostAddress().toString();
        } catch (Exception e) {
            return "";
        }

    }
}
