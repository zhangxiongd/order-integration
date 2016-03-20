package me.smart.order.enums;

/**
 * Created by zhangxiong on 16/3/19.
 */
public enum PaymentOrderStatus {
    PROCESS(0, "未支付"),
    SUCCESS(1, "成功"),
    CANCEL(2, "取消"),
    TIMEOUT(3, "超时"),
    REFUND(4, "退款");

    private int status;
    private String statusDesc;

    PaymentOrderStatus(int status, String statusDesc) {
        this.status = status;
        this.statusDesc = statusDesc;
    }

    public static PaymentOrderStatus parse(int status) {
        for (PaymentOrderStatus orderStatus : PaymentOrderStatus.values()) {
            if (orderStatus.status == status) {
                return orderStatus;
            }
        }
        return null;
    }

    public int getStatus() {
        return status;
    }

    public String getStatusDesc() {
        return statusDesc;
    }

}
