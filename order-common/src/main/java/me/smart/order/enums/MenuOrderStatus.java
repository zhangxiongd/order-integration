package me.smart.order.enums;

/**
 * Created by zhangxiong on 16/3/13.
 */
public enum MenuOrderStatus {
    INITIALIZATION(0, "待支付"),
    PENDING(1, "待处理"),
    REJECT(2, "已拒单"),
    CANCEL(3, "取消"),
    PROCESS(4, "烹饪中"),
    SUCCESS(5, "成功");

    private int status;
    private String statusDesc;

    MenuOrderStatus(int status, String statusDesc) {
        this.status = status;
        this.statusDesc = statusDesc;
    }

    public static MenuOrderStatus parse(int status) {
        for (MenuOrderStatus menuOrderStatus : MenuOrderStatus.values()) {
            if (menuOrderStatus.status == status) {
                return menuOrderStatus;
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
