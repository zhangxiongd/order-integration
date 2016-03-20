package me.smart.order.enums;

/**
 * Created by zhangxiong on 16/3/19.
 */
public enum RefundStatus {
    SUCCESS(1, "退款成功"),
    FAIL(2, "退款失败"),
    PROCESSING(3, "退款中"),
    NOTSURE(4, "未确定"),
    MANUAL_INTERVENTION(5, "转入代发"),
    WAIT_REFUND(6, "等待退款");

    private final int code;
    private final String desc;

    RefundStatus(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static RefundStatus parse(int code) {
        for (RefundStatus status : RefundStatus.values()) {
            if (status.code == code) {
                return status;
            }
        }
        return null;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

}
