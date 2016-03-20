package me.smart.order.constant;

/**
 * Created by zhangxiong on 16/1/16.
 */
public class TenPayConstant {
    //微信appId
    public static final String APP_ID = "wx2198d7e7906740d9";
    //微信appsecret
    public static final String APPSECRET = "ee25c2864dbf9c5d51e95b75592e9c9d";
    //微信支付商户号
    public static final String MCHID = "1315242501";

    //商户秘钥
    public static String KEY = "44692565cddd43609a45b80df7a25c26";

    // 以下是几个API的路径：
    //1）被扫支付API
    public static final String PAY_API = "https://api.mch.weixin.qq.com/pay/micropay";
    // 2）查询订单API
    public static final String PAY_QUERY_API = "https://api.mch.weixin.qq.com/pay/orderquery";
    // 3）退款API
    public static final String REFUND_API = "https://api.mch.weixin.qq.com/secapi/pay/refund";
    // 4）退款查询API
    public static final String REFUND_QUERY_API = "https://api.mch.weixin.qq.com/pay/refundquery";
    // 8) 统一下单API(除被扫支付外，其他微信支付方式均需调用该接口)
    public static final String UNIFIED_ORDER_API = "https://api.mch.weixin.qq.com/pay/unifiedorder";
    // 8) 关闭单API(除被扫支付外，其他微信支付方式均需调用该接口)
    public static final String CLOSER_ORDER_API = "https://api.mch.weixin.qq.com/pay/closeorder";


    public static String getAuthUrl(String redirectUrl, String state) {
        return "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + APP_ID + "&redirect_uri=" + redirectUrl +
                "&response_type=code&scope=snsapi_userinfo&state=" + state + "#wechat_redirect";
    }
}
