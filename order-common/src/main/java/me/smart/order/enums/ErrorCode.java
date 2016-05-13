package me.smart.order.enums;

/**
 * Created by zhangxiong on 16/3/19.
 */
public enum ErrorCode {
    // 0001~0999：通用错误
    HTTP_CONN_ERROR("0001", "HTTP通讯错误"), JSON_FORMAT_ERROR("0002", "Json转化异常"), MER_ORDER_EMPTY("0003", "商户订单为空"),
    // 1000~1999：混付流程错误


    REDIS_ERROR("2004", "redis出错"),
    SIGN_ERROR("2005", "签名错误"),
    PARSE_ERROR("2006", "转化异常"),
    SQL_ERROR("2007", "数据库操作错误"),


    // 3000~3999：三方支付错误
    //3000-3099:请求微信错误
    TENPAY_SYS_ERROR("3001", "请求微信返回为空"), TENPAY_PARA_ERROR

            ("3002", "请求微信参数错误"),

    TENPAY_SIGN_ERROR("3003", "请求微信返回签名错误"),

    TENPAY_PAY_TYPE_ERROR("3004", "请求微信支付类型错误"), TENPAY_TRADE_TYPE_ERROR

            ("3005", "请求微信交易方式错误"),

    TENPAY_GENERATE_SIGN_ERROR("3006", "生成微信签名错误"), TENPAY_PAY_QUERY_RESULT_ERROR

            ("3007", "微信支付查询返回结果错误"),

    TENPAY_PAY_REQ_ERROR("3008", "微信支付请求错误"), TENPAY_PAY_RESULT_ERROR

            ("3009", "微信支付返回结果错误"),

    TENPAY_REFUND_QUERY_RESULT_ERROR("3010", "微信退款查询返回结果错误"), TENPAY_REFUND_RESULT_ERROR

            ("3011", "微信退款返回结果错误"),

    TENPAY_CONFIG_SIGN_ERROR("3012", "微信config sign 错误"), TENPAY_PAYAMOUNT_ERROR

            ("3013", "微信返回金额错误"),

    TENPAY_NOTIFY_ERROR("3014", "微信返回通知错误"),

    TENPAY_OPEN_ID_ERROR("3015", "微信openId错误");

    private String code, desc;

    ErrorCode(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
