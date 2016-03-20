package me.smart.order.enums;

/**
 * Created by zhangxiong on 16/3/19.
 */
public enum PayCatalog {
    THIRD_PAY("三方支付");

    private String desc;

    PayCatalog(String desc) {
        this.desc = desc;
    }

    public static PayCatalog parse(String name) {
        try {
            return PayCatalog.valueOf(name);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    public String getDesc() {
        return desc;
    }

}
