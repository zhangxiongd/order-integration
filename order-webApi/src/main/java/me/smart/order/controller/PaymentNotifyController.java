package me.smart.order.controller;

import me.smart.order.service.PaymentNotifyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

/**
 * Created by zhangxiong on 16/3/12.
 */
@RequestMapping(value = "/notify", method = {RequestMethod.OPTIONS, RequestMethod.POST, RequestMethod.GET})
@Controller
public class PaymentNotifyController {

    private Logger logger = LoggerFactory.getLogger(PaymentNotifyController.class);

    @Resource
    private PaymentNotifyService paymentNotifyService;


    /**
     * 微信回调
     *
     * @param msg
     */
    @RequestMapping(value = "tenpay")
    public String acceptTenPayNotify(@RequestBody String msg) {
        logger.info("微信支付回调通知: {}", msg);
        try {
            return paymentNotifyService.acceptTenPayNotify(msg);
        } catch (Exception e) {
            return "error";
        }
    }
}
