package me.smart.order.controller;

import me.smart.order.api.Result;
import me.smart.order.api.merchant.request.MenuOrderDealRequest;
import me.smart.order.api.merchant.request.MenuOrderListRequest;
import me.smart.order.service.MerchantMenuOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by zhangxiong on 16/4/3.
 */
@Controller
@RequestMapping("/merchant/order")
public class MerchantMenuOrderController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(MerchantMenuOrderController.class);

    @Resource
    private MerchantMenuOrderService merchantMenuOrderService;

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    private Result list(@RequestBody MenuOrderListRequest request) {
        logger.info("MerchantMenuOrderController list param={}", request);
        try {
            request.validate();
            checkSign(request);
            return merchantMenuOrderService.list(request);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return errorResult(e);
        }
    }


    @RequestMapping(value = "/deal", method = RequestMethod.POST)
    @ResponseBody
    private Result dealMenuOrder(@RequestBody MenuOrderDealRequest request) {
        logger.info("MerchantMenuOrderController list param={}", request);
        try {
            request.validate();
            checkSign(request);
            return merchantMenuOrderService.deal(request);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return errorResult(e);
        }
    }
}
