package me.smart.order.controller;

import me.smart.order.api.Request.QueryMenuRequest;
import me.smart.order.api.Response.MerchantMenuResponse;
import me.smart.order.api.Result;
import me.smart.order.service.MenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by zhangxiong on 16/1/5.
 */
@Controller
@RequestMapping("/merchantMenu")
public class MenuController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(BaseController.class);

    @Resource
    private MenuService menuService;

    /**
     * 根据merchantId获取餐厅列表
     *
     * @param queryMenuRequest
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/index", method = RequestMethod.POST)
    @ResponseBody
    public Result<MerchantMenuResponse> queryMenuByMerchantId(@RequestBody QueryMenuRequest queryMenuRequest) {
        logger.info("MenuController queryMenuByMerchantId param={}", queryMenuRequest);
        try {
            queryMenuRequest.validate();
            checkSign(queryMenuRequest);
            MerchantMenuResponse merchantMenuResponse = menuService.getMerchantMenuList(Long.valueOf(queryMenuRequest.getMerchantId()));
            return Result.createResult(merchantMenuResponse);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return errorResult(e);
        }

    }

}
