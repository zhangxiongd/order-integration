package me.smart.order.controller;

import me.smart.order.api.Request.MenuOrderRequest;
import me.smart.order.api.Response.MenuOrderResponse;
import me.smart.order.api.Result;
import me.smart.order.enums.MenuOrderStatus;
import me.smart.order.model.MenuOrder;
import me.smart.order.service.MenuOrderService;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.math.BigDecimal;

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
    public Result<MenuOrderResponse> order(@RequestBody MenuOrderRequest menuOrderRequest) {
        logger.info("MenuOrderController order requestParam = {}", menuOrderRequest);
        //todo 验签
        //todo 防止重复下单
        try {
            MenuOrder menuOrder = menuOrderService.transMenuOrder(menuOrderRequest);
            MenuOrderResponse menuOrderResponse = convertToResponse(menuOrderRequest, menuOrder);
            return Result.createResult(menuOrderResponse);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return errorResult(e);
        }

    }

    private MenuOrderResponse convertToResponse(MenuOrderRequest menuOrderRequest, MenuOrder menuOrder) {
        MenuOrderResponse menuOrderResponse = new MenuOrderResponse();
        menuOrderResponse.setMenuOrderNo(menuOrder.getMenuOrderNo());
        menuOrderResponse.setTableNo(menuOrder.getTableNo());
        menuOrderResponse.setMerchantName("");
        menuOrderResponse.setOrderStatus(menuOrder.getOrderStatus());
        menuOrderResponse.setOrderStatusDesc(MenuOrderStatus.parse(menuOrder.getOrderStatus().intValue()).getStatusDesc());
        menuOrderResponse.setOrderTime(new DateTime(menuOrder.getCreatedAt()).toString("yyyy-MM-dd HH:mm:ss"));
        menuOrderResponse.setRemark(menuOrder.getRemark());
        menuOrderResponse.setTotalAmount(menuOrder.getTotalAmount().divide(new BigDecimal(100)).toString());
        menuOrderResponse.setMenuOrderCourseInfoList(menuOrderRequest.getMenuOrderCourseInfoList());
        return menuOrderResponse;

    }
}
