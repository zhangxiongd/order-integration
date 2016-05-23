package me.smart.order.controller;

import me.smart.order.api.Result;
import me.smart.order.api.member.Request.MenuOrderRequest;
import me.smart.order.api.member.Response.PayResult;
import me.smart.order.service.MenuOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by zhangxiong on 16/2/2.
 * 处理菜品订单的controller
 * 下单 以及查询菜品订单
 */
@Controller
@RequestMapping("/menuOrder")
public class MenuOrderController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(MenuOrderController.class);

    @Resource
    private MenuOrderService menuOrderService;

    @RequestMapping(value = "/order", method = RequestMethod.POST)
    @ResponseBody
    public Result order(@RequestBody MenuOrderRequest menuOrderRequest) {
        logger.info("MenuOrderController order requestParam = {}", menuOrderRequest);
        try {
            menuOrderRequest.validate();
            Result<PayResult> result = menuOrderService.transMenuOrder(menuOrderRequest);
            return result;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return errorResult(e);
        }
    }

    @RequestMapping(value = "/menuOrderQuery", method = RequestMethod.POST)
    @ResponseBody
    public Result menuOrderQuery(){
        return null;
    }
}
