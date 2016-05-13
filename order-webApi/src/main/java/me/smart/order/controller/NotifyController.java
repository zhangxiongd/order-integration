package me.smart.order.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by zhangxiong on 16/3/12.
 */
@RequestMapping(value = "/notify", method = {RequestMethod.OPTIONS, RequestMethod.POST, RequestMethod.GET})
@Controller
public class NotifyController {

    private Logger logger = LoggerFactory.getLogger(NotifyController.class);


    /**
     * 微信回调
     *
     * @param msg
     */
    @RequestMapping(value = "tenpay")
    public void acceptTenPayNotify(@RequestBody String msg) {
        
        logger.info("微信支付回调通知: {}", msg);

    }
}
