package me.smart.order.controller;

import me.smart.order.api.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by zhangxiong on 16/1/14.
 */
@Controller
@RequestMapping("payment")
public class PaymentController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(PaymentController.class);

    public Result prepay() {
        return null;
    }
}
