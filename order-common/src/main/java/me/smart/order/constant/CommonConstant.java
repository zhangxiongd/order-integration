package me.smart.order.constant;

import me.smart.order.enums.SourceEnums;

/**
 * Created by zhangxiong on 16/3/30.
 */
public class CommonConstant {
    //android ios 的签名authKey
    public static final String ANDROID_AUTH_KEY = "";
    public static final String IOS_AUTH_KEY = "";

    public static String getAuthKey(int source) {
        if (SourceEnums.MERCHANT_ANDROID.getSource() == source) {
            return ANDROID_AUTH_KEY;
        } else if (SourceEnums.MERCHANT_IOS.getSource() == source) {
            return IOS_AUTH_KEY;
        } else {
            return "";
        }
    }

}
