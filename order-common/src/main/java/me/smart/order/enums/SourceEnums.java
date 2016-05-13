package me.smart.order.enums;

/**
 * Created by zhangxiong on 16/3/30.
 */
public enum SourceEnums {
    MERCHANT_ANDROID(1),
    MERCHANT_IOS(2);

    private int source;

    SourceEnums(int source) {
        this.source = source;
    }


    public int getSource() {
        return source;
    }


}
