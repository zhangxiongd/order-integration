package me.smart.order.business.tenpay;

import me.smart.order.constant.TenPayConstant;
import me.smart.order.enums.TradeType;
import me.smart.order.util.RandomStringGenerator;
import me.smart.order.util.TenPaySignature;
import org.slf4j.Logger;

import java.util.*;

public class TenPayResultBusiness {

    private static Logger logger = org.slf4j.LoggerFactory.getLogger(TenPayResultBusiness.class);

    private static String createLinkString(Map<String, Object> params) {

        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);

        StringBuffer linkBuffer = new StringBuffer();
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            linkBuffer.append(key + "=" + params.get(key));
            if (i != keys.size() - 1) {//拼接时，不包括最后一个&字符
                linkBuffer.append("&");
            }
        }
        return linkBuffer.toString();
    }

    /**
     * 暂只支持微信的JSAPI支付
     *
     * @param prepayId  预支付
     * @param tradeType 交易类型
     * @return
     */
    public static String getDetail(String prepayId, String tradeType) {
        if (TradeType.JSAPI.name().equals(tradeType)) {
            long time = System.currentTimeMillis() / 1000;
            Map<String, Object> signMap = new HashMap<String, Object>();
            signMap.put("appId", TenPayConstant.APP_ID);
            signMap.put("timeStamp", String.valueOf(time));
            signMap.put("nonceStr", RandomStringGenerator.getRandomStringByLength(32));
            signMap.put("package", "prepay_id=" + prepayId);
            signMap.put("signType", "MD5");
            String sign = TenPaySignature.getSign(signMap);
            logger.info("-----微信JSAPI签名------------" + sign);
            signMap.put("paySign", sign);
            return createLinkString(signMap);
        } else {
            return "";
        }

////        if (PayTradeType.APP.name().equals(tradeType)) {
////            if (merchantId == 2) {
////                signMap.put("appid", WeixinConstant.getAppIDForApp());
////                signMap.put("partnerid", WeixinConstant.getMchIDForApp());
////            } else {
////                signMap.put("appid", WeixinConstant.getAppIDForApp_ELE());
////                signMap.put("partnerid", WeixinConstant.getMchIDForApp_ELE());
////            }
////            signMap.put("prepayid", prepayId);
////            signMap.put("package", "Sign=WXPay");
////            signMap.put("noncestr", RandomStringGenerator.getRandomStringByLength(32));
////            signMap.put("timestamp", String.valueOf(time));
////            String sign = WeixinSignature.getSign(signMap, "APP", merchantId);
////
////            logger.info("-----微信APP签名-----》》》》----------" + sign);
////            signMap.put("sign", sign);
////
////            return createLinkString(signMap);
//
//        } else if (PayTradeType.JSAPI.name().equals(tradeType)) {
//            signMap.put("appId", WeixinConstant.getAppID());
//            signMap.put("timeStamp", String.valueOf(time));
//            signMap.put("nonceStr", RandomStringGenerator.getRandomStringByLength(32));
//            signMap.put("package", "prepay_id=" + prepayId);
//            signMap.put("signType", "MD5");
//            String sign = WeixinSignature.getSign(signMap, "JSAPI", merchantId);
//            logger.info("-----微信JSAPI签名-----》》》》----------" + sign);
//            signMap.put("paySign", sign);
//            return createLinkString(signMap);
//        } else if (PayTradeType.WAP.name().equals(tradeType)) {
//            signMap.put("appid", WeixinConstant.getAppID());
//            signMap.put("noncestr", RandomStringGenerator.getRandomStringByLength(32));
//            signMap.put("package", "WAP");
//            signMap.put("prepayid", prepayId);
//            signMap.put("timestamp", String.valueOf(time));
//
//            String sign = WeixinSignature.getSign(signMap, "WAP", merchantId);
//            signMap.put("sign", sign);
//
//            logger.info("微信WAP签名：" + sign);
//            logger.info("微信WAP支付组返回deepLink");
//            //遍历signMap,组返回的deeplink
//
//            try {
//                StringBuilder stringBuilder = new StringBuilder();
//                stringBuilder.append("appid=").append(URLEncoder.encode(String.valueOf(signMap.get("appid")), "UTF-8"))
//                        .append("&");
//                stringBuilder.append("noncestr=")
//                        .append(URLEncoder.encode(String.valueOf(signMap.get("noncestr")), "UTF-8")).append("&");
//                stringBuilder.append("package=")
//                        .append(URLEncoder.encode(String.valueOf(signMap.get("package")), "UTF-8")).append("&");
//                stringBuilder.append("prepayid=")
//                        .append(URLEncoder.encode(String.valueOf(signMap.get("prepayid")), "UTF-8")).append("&");
//                stringBuilder.append("sign=").append(URLEncoder.encode(String.valueOf(signMap.get("sign")), "UTF-8"))
//                        .append("&");
//                stringBuilder.append("timestamp=").append(
//                        URLEncoder.encode(String.valueOf(signMap.get("timestamp")), "UTF-8"));
//                StringBuilder deepLink = new StringBuilder();
//                deepLink.append("weixin://wap/pay?").append(URLEncoder.encode(stringBuilder.toString(), "UTF-8"));
//                return deepLink.toString();
//            } catch (UnsupportedEncodingException ex) {
//                logger.error("微信支付请求错误", ex);
//                throw new SystemException(ErrorCode.WEIXIN_PAY_REQ_ERROR, ErrorCode.WEIXIN_PAY_REQ_ERROR.getDesc());
//            }
//        } else if (PayTradeType.NATIVE.name().equals(tradeType)) {
//            return "native";
//        }
//        return null;
    }

}
