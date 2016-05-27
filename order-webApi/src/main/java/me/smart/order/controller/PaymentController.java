package me.smart.order.controller;

import me.smart.order.api.Result;
import me.smart.order.api.member.Request.CancelRequest;
import me.smart.order.service.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * Created by zhangxiong on 16/1/14.
 */
@Controller
@RequestMapping("payment")
public class PaymentController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(PaymentController.class);

    @Resource
    private PaymentService paymentService;

    /**
     * 取消订单
     * 只有当商家未处理订单的时候 才可以取消订单
     *
     * @param cancelRequest
     * @return
     */
    public Result cancel(@RequestBody CancelRequest cancelRequest) {
        try {
            cancelRequest.validate();
        } catch (Exception e) {
        }
        return null;
    }

}
