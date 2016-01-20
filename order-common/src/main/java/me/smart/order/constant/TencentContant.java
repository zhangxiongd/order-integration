package me.smart.order.constant;

/**
 * Created by zhangxiong on 16/1/16.
 */
public class TencentContant {
    //微信appId
    public static final String APP_ID = "wx3c13e8b50ceebb03";
    //微信appsecret
    public static final String APPSECRET = "d4624c36b6795d1d99dcf0547af5443d";
    public static final String MCHID = "";

    //商户秘钥
    public static String KEY = "cad83d8c7b0f7c5998acb3d52571320e";

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

}
