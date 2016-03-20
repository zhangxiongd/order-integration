package me.smart.order.enums;

/**
 * Created by zhangxiong on 16/3/20.
 */
public enum MemberUnionEnums {
    TEN_SERVICE(1, "服务号"),
    TEN_APP(2, "APP"),
    TEN_SUBSCRIPTION(3, "订阅号");

    private int source;
    private String desc;

    MemberUnionEnums(int source, String desc) {
        this.source = source;
        this.desc = desc;
    }


    public int getSource() {
        return source;
    }

    public String getDesc() {
        return desc;
    }
}
