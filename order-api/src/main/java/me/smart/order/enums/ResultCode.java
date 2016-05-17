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


    // ======== 以下为错误码 ========

    //    @Deprecated
    //短信和推送相关
    VERIFY_CODE_SEND_ERROR("2002", "短信发送失败"),

    VERIFY_CODE_VALIDATE_ERROR("2003", "短信无效"),

    VERIFY_CODE_TIMEOUT_ERROR("2004", "短信超时"),

    SYSTEM_ERROR("2094", "系统异常"),

    // 报文相关： 系统相关
    @Deprecated
    PARSE_ERROR("1001", "对象转化异常"),
    VALIDATE_PARAM_ERROR("1002", "必填公共参数为空"),
    REQUEST_NOT_VALID("1003", "报文非法"),
    OPERATION_VALID("1004", "非法操作"),

    SIGN_ERROR("1005", "签名错误"),
    AUTH_KEY_ERROR("1006", "签名key为空"),
    REDIS_KEY_ERROR("1007", "redis key不存在"),
    SQL_ERROR("1008", "数据库操作错误"),

    @Deprecated
    PAY_SUCCESS("1111", "支付成功"),


    // 用户相关
    MEMBER_NOT_EXIST_ERROR("3001", "用户不存在"),

    //商户相关
    MERCHANT_NOT_EXIST_ERROR("4001", "商户不存在"),

    MERCHANT_ACCOUNT_NOT_EXIST_ERROR("4002", "商户账号不存在"),

    MERCHANT_ACCOUNT_EXISTED_ERROR("4003", "商户账号已存在"),

    MERCHANT_ACCOUNT_PASSWORD_ERROR("4004", "商户账号密码错误"),

    ORDER_NOT_EXIST_ERROR("4005", "订单不存在"),
    CATEGORY_EXISTED_ERROR("4006", "菜品分类已存在"),
    COURSE_EXISTED_ERROR("4007", "菜品已存在"),
    COURSE_NOT_EXIST_ERROR("4008", "菜品不存在"),
    CATEGORY_NOT_EXIST_ERROR("4009", "菜品分类不存在"),
    ORDER_STATUS_ERROR("4010", "订单状态错误"),


    MERCHANT_COURSE_CATEGORY_EXISTED_ERROR("4010", "打印分类已存在"),
    MERCHANT_COURSE_CATEGORY_NOT_EXIST_ERROR("4011", "打印分类不存在"),

    //微信接口相关
    TENPAY_SYS_ERROR("5006", "微信API请求逻辑错误");


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
