package me.smart.order.weixin;

import me.smart.order.constant.TenPayConstant;
import me.smart.order.util.DateUtil;
import me.smart.order.util.HttpCallService;
import me.smart.order.util.JsonParser;
import me.smart.order.util.RandomStringGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhangxiong on 16/1/17.
 */
@Component
public class WeixinSignService {
    private Logger logger = LoggerFactory.getLogger(WeixinSignService.class);

    @Resource
    private WeixinAccessTokenCache weixinAccessTokenCache;

    public Map<String, Object> getWeixinConfigSign(String url) throws Exception {
        //1获取access_token
        WeixinAccessToken accestoken = weixinAccessTokenCache.getAccessToken();
        WeixinJsapiTicket jsapiTicket = weixinAccessTokenCache.getJsapiTicket();

        synchronized (WeixinAccessToken.class) {
            if (accestoken == null) {
                applyAccessToken(accestoken, jsapiTicket);
            } else {
                //判断是否过去
                Date currentTime = new Date();
                Date oldApplyTime = accestoken.getApplyDate();
                int effectiveTime = accestoken.getValidateTime() - 1;
                long diffTime = DateUtil.getDiffTime(currentTime, oldApplyTime);
                if (diffTime > effectiveTime) {
                    //如果时间差大于有效期
                    //重新申请acceeToken
                    applyAccessToken(accestoken, jsapiTicket);
                }
            }
        }
        //获取jsapiTicket 组签名
        accestoken = weixinAccessTokenCache.getAccessToken();
        jsapiTicket = weixinAccessTokenCache.getJsapiTicket();
        String jsapi_ticket = jsapiTicket.getTicket();
        long timestamp = System.currentTimeMillis() / 1000;
        String noncestr = RandomStringGenerator.getRandomStringByLength(20);
        Map<String, Object> signMap = new HashMap<String, Object>();
        signMap.put("jsapi_ticket", jsapi_ticket);
        signMap.put("timestamp", String.valueOf(timestamp));
        signMap.put("url", url);
        signMap.put("noncestr", noncestr);
        String sign = WeixinSignature.getSignSha1(signMap);
        signMap.put("sign", sign);
        return signMap;
    }

    private void applyAccessToken(WeixinAccessToken accestoken, WeixinJsapiTicket weixinJsapiTicket) throws Exception {
        HttpCallService httpCallService = new HttpCallService();
        String appId = TenPayConstant.APP_ID;
        String appScert = TenPayConstant.APPSECRET;
        String accessUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + appId + "&secret=" + appScert;
        String accessResponse = httpCallService.get(accessUrl);
        //将返回的json串转为map
        Map map = JsonParser.convertToMap(accessResponse);
        String acceTokenValue = (String) map.get("access_token");
        int expires_in = Integer.valueOf(map.get("expires_in").toString());
        if (accestoken == null) {
            accestoken = new WeixinAccessToken();
        }
        accestoken.setAccessTokenValue(acceTokenValue);
        accestoken.setValidateTime(expires_in);
        accestoken.setApplyDate(new Date());
        //用获得的accToken获取jsapiTicket
        String jsapiTickentUrl = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=" + acceTokenValue + "&type=jsapi";
        HttpCallService httpCall1 = new HttpCallService();
        String jsapiResponse = httpCall1.get(jsapiTickentUrl);
        Map jsapiMap = JsonParser.convertToMap(jsapiResponse);
        int errorCode = ((Integer) jsapiMap.get("errcode")).intValue();
        String errorMsg = (String) jsapiMap.get("errmsg");
        if (weixinJsapiTicket == null) {
            weixinJsapiTicket = new WeixinJsapiTicket();
        }
        if ("ok".equals(errorMsg)) {
            String ticket = (String) jsapiMap.get("ticket");
            int jsapiExpires_in = ((Integer) jsapiMap.get("expires_in")).intValue();
            weixinJsapiTicket.setApplyDate(new Date());
            weixinJsapiTicket.setErrorCode(errorCode);
            weixinJsapiTicket.setErrorMsg(errorMsg);
            weixinJsapiTicket.setExpires_in(jsapiExpires_in);
            weixinJsapiTicket.setTicket(ticket);
        } else {
            logger.error("获取微信jsapi失败 errorCode = " + errorCode + " errorMsg = " + errorMsg);
        }
        weixinAccessTokenCache.setAccessToken(accestoken);
        weixinAccessTokenCache.setJsapiTicket(weixinJsapiTicket);

    }

    /**
     * 根据auth_code 获取openId和access_token
     * {
     * "access_token":"ACCESS_TOKEN",
     * "expires_in":7200,
     * "refresh_token":"REFRESH_TOKEN",
     * "openid":"OPENID",
     * "scope":"SCOPE",
     * "unionid": "o6_bmasdasdsad6_2sgVt7hMZOPfL"
     * }
     *
     * @param code
     * @return
     * @throws Exception
     */
    public Map<String, Object> getOpenId(String code) throws Exception {
        HttpCallService httpCallService = new HttpCallService();
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" +
                TenPayConstant.APP_ID + "&secret=" + TenPayConstant.APPSECRET + "&code=" + code + "&grant_type=authorization_code";
        String result = httpCallService.get(url);
        logger.info("获取用户openId={}", result);
        return JsonParser.convertToMap(result);
    }

    /**
     * 根据accessToken 和  openId 获取微信userInfo
     * <p>
     * "openid":" OPENID",
     * " nickname": NICKNAME,
     * "sex":"1",
     * "province":"PROVINCE"
     * "city":"CITY",
     * "country":"COUNTRY",
     * "headimgurl":    "http://wx.qlogo.cn/mmopen/g3MonUZtNHkdmzicIlibx6iaFqAc56vxLSUfpb6n5WKSYVY0ChQKkiaJSgQ1dZuTOgvLLrhJbERQQ4eMsv84eavHiaiceqxibJxCfHe/46",
     * "privilege":[
     * "PRIVILEGE1"
     * "PRIVILEGE2"
     * ],
     * "unionid": "o6_bmasdasdsad6_2sgVt7hMZOPfL"
     * }
     *
     * @param accessToken
     * @param openId
     * @return
     */
    public Map<String, Object> getUserInfo(String accessToken, String openId) throws Exception {
        String url = "https://api.weixin.qq.com/sns/userinfo?access_token=" + accessToken + "&openid=" + openId + "&lang=zh_CN";
        HttpCallService httpCallService = new HttpCallService();
        String result = httpCallService.get(url);
        logger.info("微信用户openId的用户信息,userInfo={}", result);
        return JsonParser.convertToMap(result);
    }


}
