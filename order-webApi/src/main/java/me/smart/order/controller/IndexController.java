package me.smart.order.controller;

import me.smart.order.dao.MerchantMapper;
import me.smart.order.weixin.WeixinSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by zhangxiong on 15/12/19.
 */
@Controller
@RequestMapping("/index")
public class IndexController {


    private Logger log = LoggerFactory.getLogger(IndexController.class);


    @Resource
    private MerchantMapper merchantHashtableMapper;

    @RequestMapping(value = "/check")
    @ResponseBody
    public String check(HttpServletRequest request, HttpServletResponse response) {
        log.info("check server");
        Map<String, Object> requestMap = requestToMap(request);
        log.info("request map = {}", requestMap);
        String timestamp = requestMap.get("timestamp").toString();
        String noncestr = requestMap.get("nonce").toString();
        String token = "m867lpdsef0p02vw6xbyjxacjiclvtlu";


        Map<String, Object> signMap = new HashMap<String, Object>();
        signMap.put("timestamp", timestamp);
        signMap.put("noncestr", noncestr);
        signMap.put("token", token);

        String sign = WeixinSignature.getSignSha1(signMap);
        log.info("sign ={}", sign);
        log.info("request sign ={}", requestMap.get("signature").toString());
        if (sign.equals(requestMap.get("signature").toString())) {
            return requestMap.get("echostr").toString();
        }
        Cookie cookie = new Cookie("name", "zhangxiong");
        cookie.setDomain(".mydian.net");
        cookie.setPath("/");
        cookie.setMaxAge(Integer.MAX_VALUE);
        response.addCookie(cookie);
        return "";
    }

    private static Map requestToMap(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            Map requestParams = request.getParameterMap();
            String name = null;
            String[] values = null;
            //            String valueStr = "";
            for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
                name = (String) iter.next();
                values = (String[]) requestParams.get(name);
                map.put(name, values[0]);
            }
        } catch (Exception e) {

        }
        return map;
    }


}
