package me.smart.order.weixin;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by zhangxiong on 16/1/16.
 */
@Component("weixinAccessTokenCache")
@Scope(value = "singleton")
public class WeixinAccessTokenCache {
    private WeixinAccessToken accessToken;

    private WeixinJsapiTicket jsapiTicket;

    public WeixinAccessToken getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(WeixinAccessToken accessToken) {
        this.accessToken = accessToken;
    }

    public WeixinJsapiTicket getJsapiTicket() {
        return jsapiTicket;
    }

    public void setJsapiTicket(WeixinJsapiTicket jsapiTicket) {
        this.jsapiTicket = jsapiTicket;
    }
}
