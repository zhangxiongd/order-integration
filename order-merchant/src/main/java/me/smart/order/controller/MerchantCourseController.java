package me.smart.order.controller;

import me.smart.order.api.Result;
import me.smart.order.api.merchant.request.*;
import me.smart.order.service.MerchantCourseService;
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
    public Result addCategory(@RequestBody CategoryAddRequest request) {
        logger.info("MerchantCourseController addCategory request={}", request);
        try {
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
    public Result deleteCategory(@RequestBody CategoryDeleteRequest request) {
        logger.info("MerchantCourseController deleteCategory request={}", request);
        try {
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
    public Result updateCategory(@RequestBody CategoryUpdateRequest request) {
        logger.info("MerchantCourseController updateCategory request={}", request);
        try {
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
    public Result listCategory(@RequestBody CategoryListRequest request) {
        logger.info("MerchantCourseController listCategory request={}", request);
        try {
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
    public Result addCourse(@RequestBody CourseAddRequest request) {
        logger.info("MerchantCourseController addCourse request={}", request);
        try {
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
    public Result updateCourse(@RequestBody CourseUpdateRequest request) {
        logger.info("MerchantCourseController updateCourse request={}", request);
        try {
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
    public Result listCourseByCategory(@RequestBody CourseListRequest request) {
        logger.info("MerchantCourseController listCourseByCategory request={}", request);
        try {
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
    public Result shelf(@RequestBody CourseShelfRequest request) {
        logger.info("MerchantCourseController shelf request={}", request);
        try {
            request.validate();
            checkSign(request);
            return merchantCourseService.shelf(request);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return errorResult(e);
        }
    }

}
