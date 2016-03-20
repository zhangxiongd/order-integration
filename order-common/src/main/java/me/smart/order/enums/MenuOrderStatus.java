package me.smart.order.enums;

/**
 * Created by zhangxiong on 16/3/13.
 */
public enum MenuOrderStatus {
    PENDING(0, "待处理"),
    PROCESS(1, "烹饪中");

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
