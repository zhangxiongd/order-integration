package me.smart.order.enums;

/**
 * Created by zhangxiong on 16/3/19.
 */
public enum PaymentRecordStatus {
    NOTPAY(0, "未支付"),
    SUCCESS(1, "支付成功"),
    USERPAYING(2, "用户支付中"),
    PAYERROR(3, "支付失败"),
    REFUND(4, "转入退款");

    private int code;
    private String label;

    PaymentRecordStatus(int code, String label) {
        this.code = code;
        this.label = label;
    }

    public static PaymentRecordStatus parse(int code) {
        for (PaymentRecordStatus status : PaymentRecordStatus.values()) {
            if (status.code == code) {
                return status;
            }
        }
        return null;
    }

    public int getCode() {
        return code;
    }

    public String getLabel() {
        return label;
    }
}
