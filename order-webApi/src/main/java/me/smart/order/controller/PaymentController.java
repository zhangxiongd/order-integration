package me.smart.order.controller;

import me.smart.order.api.Result;
import me.smart.order.api.member.Request.CancelRequest;
import me.smart.order.service.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

/**
 * Created by zhangxiong on 16/1/14.
 */
@Controller
@RequestMapping("/payment")
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
    @RequestMapping(value = "/cancel",method = RequestMethod.POST)
    public Result cancel(@RequestBody CancelRequest cancelRequest) {
        try {
            cancelRequest.validate();
            paymentService.cancel(cancelRequest);
            return Result.createResult(null);
        } catch (Exception e) {
            logger.error("取消订单失败，请稍后重试",e);
           return errorResult(e);
        }
    }

}
