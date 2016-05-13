package me.smart.order.enums;

/**
 * Created by zhangxiong on 16/4/1.
 */
public enum AccountOperationType {
    REGISTER_ACCOUNT(1, "注册用户"),
    FORGET_PWD(2, "忘记密码"),
    LOGIN_ACCOUNT(3, "登录帐户"),
    MODIFY_PWD(4, "修改密码");

    private int code;
    private String desc;

    AccountOperationType(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }


    public static AccountOperationType parse(int code) {
        for (AccountOperationType accountOperationType : AccountOperationType.values()) {
            if (accountOperationType.getCode() == code) {
                return accountOperationType;
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
