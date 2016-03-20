package me.smart.order.enums;

/**
 * Created by zhangxiong on 16/3/19.
 */
public enum TradeType {
    JSAPI(0), NATIVE(1), APP(2), WAP(3);


    private final int value;

    private TradeType(int value) {
        this.value = value;
    }

    public static TradeType findByValue(String value) {
        switch (value) {
            case "JSAPI":
                return JSAPI;
            case "NATIVE":
                return NATIVE;
            case "APP":
                return APP;
            case "WAP":
                return WAP;
            default:
                return null;
        }
    }

    public int getValue() {
        return value;
    }
}
