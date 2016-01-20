package me.smart.order.controller;

import me.smart.order.api.Response.MerchantMenuResponse;
import me.smart.order.api.Result;
import me.smart.order.exception.BusinessException;
import me.smart.order.service.MenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by zhangxiong on 16/1/5.
 */
@Controller
@RequestMapping("/menu")
public class MenuController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(BaseController.class);

    @Resource
    private MenuService menuService;

    @ResponseBody
    @RequestMapping(value = "/merchantMenu/{merchantId}")
    public Result<MerchantMenuResponse> queryMerchantMenu(@PathVariable Long merchantId) {
        logger.info("根据merchantId获取餐厅菜单,merchantId={}", merchantId);
        try {
            MerchantMenuResponse response = menuService.getMerchantMenuList(merchantId);
            return Result.createResult(response);

        } catch (BusinessException e) {
            logger.info("获取餐厅菜单失败,msg={}", e);
            return errorResult(e);
        }
    }
}
