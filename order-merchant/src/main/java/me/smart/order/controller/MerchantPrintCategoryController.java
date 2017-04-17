package me.smart.order.controller;

import me.smart.order.api.Result;
import me.smart.order.api.merchant.request.MerchantCourseCategoryAddRequest;
import me.smart.order.api.merchant.request.MerchantCourseCategoryListRequest;
import me.smart.order.api.merchant.request.MerchantCourseCategoryUpdateRequest;
import me.smart.order.service.MerchantPrintCategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by zhangxiong on 16/4/18.
 */
@Controller
@RequestMapping("/merchantCategory")
public class MerchantPrintCategoryController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(MerchantPrintCategoryController.class);

    @Resource
    private MerchantPrintCategoryService merchantPrintCategoryService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Result addPrintCategory(@RequestBody MerchantCourseCategoryAddRequest request) {
        logger.info("MerchantPrintCategoryController addPrintCategory request={}", request);
        try {
            request.validate();
            checkSign(request);
            return merchantPrintCategoryService.addCategory(request);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return errorResult(e);
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Result updatePrintCategory(@RequestBody MerchantCourseCategoryUpdateRequest request) {
        logger.info("MerchantPrintCategoryController updatePrintCategory request={}", request);
        try {
            request.validate();
            checkSign(request);
            return merchantPrintCategoryService.updateCategory(request);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return errorResult(e);
        }
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public Result listPrintCategory(@RequestBody MerchantCourseCategoryListRequest request) {
        logger.info("MerchantPrintCategoryController listPrintCategory request={}", request);
        try {
            request.validate();
            checkSign(request);
            return merchantPrintCategoryService.listCategory(request);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return errorResult(e);
        }

    }


}
