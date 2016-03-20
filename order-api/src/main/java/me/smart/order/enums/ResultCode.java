package me.smart.order.enums;

/**
 * TODO: (按以下原则重构)
 * <li> 尽量精简(越少越好)
 * <li> 前端已使用到的code保留(重点)
 * <li> 返回至前端及接入方，并记录到订单表(勿记录至流水表!)
 * <li> 接口报文中的resultMsg通过properties文件配置(勿用desc描述)
 * <p>
 * Created by zhangxiong
 */
public enum ResultCode {
    SUCCESS("0000", "操作成功"),

    @Deprecated
    PAY_REQUEST_SUCCESS("0000", "支付请求成功"),

    @Deprecated
    PAY_SUCCESS("1111", "支付成功"),

    // ======== 以下为错误码 ========

    //    @Deprecated
    MERCHANT_ERROR("2001", "商户不存在"),

    // 报文相关：
    @Deprecated
    JSON_FORMAT_ERROR("1002", "报文错误"),
    @Deprecated
    REQUEST_NOT_VALID("1003", "报文非法"),
    SIGN_ERROR("1003", "报文非法"),

    SYSTEM_ERROR("2094", "系统异常"),

    MEMBER_NOT_EXIST_ERROR("3001", "用户不存在"),


    TENPAY_SYS_ERROR("4006", "微信API请求逻辑错误");


    private String code, desc;

    ResultCode(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static ResultCode parse(String resultCode) {
        for (ResultCode code : ResultCode.values()) {
            if (code.getCode().equals(resultCode)) {
                return code;
            }
        }

        return null;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
