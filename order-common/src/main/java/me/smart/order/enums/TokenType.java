package me.smart.order.enums;

/**
 * Created by zhangxiong on 16/4/1.
 */
public enum TokenType {
    REGISTER_SALT("0", "registerSalt"),
    REGISTER_TOKEN("1", "registerToken"),
    LOGIN_TOKEN("2", "loginToken"),
    MODIFY_PWD_TOKEN("3", "modifyPwdToken"),
    MODIFY_PWD_SALT("4", "modifyPwdSalt"),
    FORGET_PWD_TOKEN("5", "forgetPwdToken"),
    FORGET_PWD_SALT("6", "forgetPwdSalt");

    private String code;
    private String lable;

    TokenType(String code, String lable) {
        this.code = code;
        this.lable = lable;
    }

    public String getCode() {
        return code;
    }

    public String getLable() {
        return lable;
    }
}
