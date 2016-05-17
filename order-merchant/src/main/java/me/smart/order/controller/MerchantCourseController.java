package me.smart.order.controller;

import me.smart.order.api.Result;
import me.smart.order.api.merchant.request.CategoryAddRequest;
import me.smart.order.api.merchant.request.CategoryDeleteRequest;
import me.smart.order.api.merchant.request.CategoryListRequest;
import me.smart.order.api.merchant.request.CategoryUpdateRequest;
import me.smart.order.api.merchant.request.CourseAddRequest;
import me.smart.order.api.merchant.request.CourseListRequest;
import me.smart.order.api.merchant.request.CourseShelfRequest;
import me.smart.order.api.merchant.request.CourseUpdateRequest;
import me.smart.order.service.MerchantCourseService;
import me.smart.order.util.JsonConvertUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by zhangxiong on 16/4/13.
 */
@Controller
@RequestMapping("/course")
public class MerchantCourseController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(MerchantCourseController.class);

    @Resource
    private MerchantCourseService merchantCourseService;

    /**
     * 添加菜品分类
     *
     * @return
     */
    @RequestMapping(value = "/category/add", method = RequestMethod.POST)
    @ResponseBody
    public Result addCategory(@RequestBody String msg) {
        logger.info("MerchantCourseController addCategory request={}", msg);
        try {
            CategoryAddRequest request = JsonConvertUtils.toObject(msg, CategoryAddRequest.class);
            request.validate();
            checkSign(request);
            return merchantCourseService.addCategory(request);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return errorResult(e);
        }
    }

    /**
     * 删除分类
     *
     * @return
     */
    @RequestMapping(value = "/category/delete", method = RequestMethod.POST)
    @ResponseBody
    public Result deleteCategory(@RequestBody String msg) {
        logger.info("MerchantCourseController deleteCategory request={}", msg);
        try {
            CategoryDeleteRequest request = JsonConvertUtils.toObject(msg, CategoryDeleteRequest.class);
            request.validate();
            checkSign(request);
            return merchantCourseService.deleteCategory(request);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return errorResult(e);
        }
    }


    /**
     * 修改分类
     *
     * @return
     */
    @RequestMapping(value = "/category/update", method = RequestMethod.POST)
    @ResponseBody
    public Result updateCategory(@RequestBody String msg) {
        logger.info("MerchantCourseController updateCategory request={}", msg);
        try {
            CategoryUpdateRequest request = JsonConvertUtils.toObject(msg, CategoryUpdateRequest.class);
            request.validate();
            checkSign(request);
            return merchantCourseService.updateCategory(request);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return errorResult(e);
        }
    }

    /**
     * 查询菜品分类
     * 暂时不分页
     *
     * @return
     */
    @RequestMapping(value = "/category/list", method = RequestMethod.POST)
    @ResponseBody
    public Result listCategory(@RequestBody String msg) {
        logger.info("MerchantCourseController listCategory request={}", msg);
        try {
            CategoryListRequest request = JsonConvertUtils.toObject(msg, CategoryListRequest.class);
            request.validate();
            checkSign(request);
            return merchantCourseService.listCategory(request);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return errorResult(e);
        }
    }


    /**
     * 添加菜品
     *
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Result addCourse(@RequestBody String msg) {
        logger.info("MerchantCourseController addCourse request={}", msg);
        try {
            CourseAddRequest request = JsonConvertUtils.toObject(msg, CourseAddRequest.class);
            request.validate();
            checkSign(request);
            return merchantCourseService.addCourse(request);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return errorResult(e);
        }
    }


    /**
     * 删除菜品
     *
     * @return
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public Result deleteCourse() {
        return null;
    }


    /**
     * 更新菜品
     *
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Result updateCourse(@RequestBody String msg) {
        logger.info("MerchantCourseController updateCourse request={}", msg);
        try {
            CourseUpdateRequest request = JsonConvertUtils.toObject(msg, CourseUpdateRequest.class);
            request.validate();
            checkSign(request);
            return merchantCourseService.updateCourse(request);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return errorResult(e);
        }
    }


    /**
     * 根据category来查询course列表
     *
     * @return
     */
    @RequestMapping(value = "/listByCategory", method = RequestMethod.POST)
    @ResponseBody
    public Result listCourseByCategory(@RequestBody String msg) {
        logger.info("MerchantCourseController listCourseByCategory request={}", msg);
        try {
            CourseListRequest request = JsonConvertUtils.toObject(msg, CourseListRequest.class);
            request.validate();
            checkSign(request);
            return merchantCourseService.listCourseByCategory(request);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return errorResult(e);
        }
    }


    /**
     * 菜品上下架操作
     *
     * @return
     */
    @RequestMapping(value = "/shelf", method = RequestMethod.POST)
    @ResponseBody
    public Result shelf(@RequestBody String msg) {
        logger.info("MerchantCourseController shelf request={}", msg);
        try {
            CourseShelfRequest request = JsonConvertUtils.toObject(msg, CourseShelfRequest.class);
            request.validate();
            checkSign(request);
            return merchantCourseService.shelf(request);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return errorResult(e);
        }
    }

}
