package me.smart.order.service.impl;

import me.smart.order.api.MenuOrderCourseInfo;
import me.smart.order.api.Result;
import me.smart.order.api.merchant.request.MenuOrderDealRequest;
import me.smart.order.api.merchant.request.MenuOrderListRequest;
import me.smart.order.api.merchant.response.MenuOrderCategory;
import me.smart.order.api.merchant.response.MenuOrderDealResponse;
import me.smart.order.api.merchant.response.MenuOrderInfo;
import me.smart.order.api.merchant.response.MenuOrderListResponse;
import me.smart.order.dao.MenuCourseMapper;
import me.smart.order.dao.MenuOrderMapper;
import me.smart.order.dao.MerchantCourseCategoryMapper;
import me.smart.order.enums.ErrorCode;
import me.smart.order.enums.MenuOrderStatus;
import me.smart.order.enums.ResultCode;
import me.smart.order.exception.BusinessException;
import me.smart.order.exception.SystemException;
import me.smart.order.model.MenuCourse;
import me.smart.order.model.MenuOrder;
import me.smart.order.model.MerchantCourseCategory;
import me.smart.order.service.MerchantMenuOrderService;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangxiong on 16/4/4.
 */
@Service("merchantMenuOrderService")
public class MerchantMenuOrderServiceImpl implements MerchantMenuOrderService {

    private Logger logger = LoggerFactory.getLogger(MerchantMenuOrderServiceImpl.class);

    @Resource
    private MenuOrderMapper menuOrderMapper;
    @Resource
    private MenuCourseMapper menuCourseMapper;

    @Resource
    private MerchantCourseCategoryMapper merchantCourseCategoryMapper;

    @Override
    public Result list(MenuOrderListRequest request) throws Exception {

        List<MenuOrder> menuOrderList = menuOrderMapper.queryListByStatus(request.getPageSize(), request.getPageNo(),
                request.getStatus());

        List<MerchantCourseCategory> merchantCourseCategoryList =
                merchantCourseCategoryMapper.getListByMerchantId(Long.valueOf(request.getMerchantId()));
        MenuOrderListResponse response = new MenuOrderListResponse();
        response.setAccountId(request.getAccountId());
        response.setMerchantId(request.getMerchantId());
        List<MenuOrderInfo> orderList = new ArrayList<>(request.getPageSize());
        menuOrderList.stream().forEach(menuOrder -> {
            MenuOrderInfo menuOrderInfo = getMenuOrderListResponse(menuOrder, merchantCourseCategoryList);
            orderList.add(menuOrderInfo);
        });
        response.setOrderList(orderList);
        return Result.createResult(response);
    }


    @Override
    public Result deal(MenuOrderDealRequest request) throws Exception {
        //根据菜单订单号获取订单
        MenuOrder menuOrder = menuOrderMapper.selectByMenuOrderNO(Long.valueOf(request.getMerchantId()), request.getMenuOrderNo());
        if (menuOrder == null) {
            throw new BusinessException(ResultCode.ORDER_NOT_EXIST_ERROR);
        }
        if (MenuOrderStatus.PROCESS.getStatus() == request.getStatus()
                || MenuOrderStatus.SUCCESS.getStatus() == request.getStatus()) {
            throw new BusinessException(ResultCode.OPERATION_VALID);
        }

        //打印订单操作
        if (request.getStatus() == MenuOrderStatus.PROCESS.getStatus()) {
            menuOrder.setOrderStatus(MenuOrderStatus.PROCESS.getStatus());
        } else {
            menuOrder.setOrderStatus(MenuOrderStatus.SUCCESS.getStatus());
            //todo。。如果是咖啡等需要客户自取的餐厅，需要在此时给用户发信息（通过微信公众号）
        }

        //更新订单表
        try {
            menuOrderMapper.updateByStatus(request.getMenuOrderNo(), menuOrder.getOrderStatus());
        } catch (Exception e) {
            logger.error("更新菜单订单状态失败" + e.getMessage(), e);
            throw new SystemException(ErrorCode.SQL_ERROR);
        }

        MenuOrderDealResponse response = new MenuOrderDealResponse();
        response.setMenuOrderNo(request.getMenuOrderNo());
        response.setMerchantId(request.getMerchantId());
        return Result.createResult(response);
    }

    private MenuOrderInfo getMenuOrderListResponse(MenuOrder menuOrder, List<MerchantCourseCategory> merchantCourseCategoryList) {
        MenuOrderInfo menuOrderInfo = new MenuOrderInfo();
        menuOrderInfo.setTableNo(menuOrder.getTableNo());
        menuOrderInfo.setMenuOrderNo(menuOrder.getMenuOrderNo());
        menuOrderInfo.setCreatedTime(new DateTime(menuOrder.getCreatedAt()).toString("yyyy-MM-dd HH:mm:ss"));
        menuOrderInfo.setCustomerName("");
        menuOrderInfo.setHeaderCount(menuOrder.getHeaderCount());
        menuOrderInfo.setTotalAmount(menuOrder.getTotalAmount().divide(new BigDecimal(100)).toString());
        menuOrderInfo.setRemark(menuOrder.getRemark());
        menuOrderInfo.setStatus(menuOrder.getOrderStatus());
        menuOrderInfo.setStatusDesc(MenuOrderStatus.parse(menuOrder.getOrderStatus()).getStatusDesc());
        List<MenuCourse> menuCourseList = menuCourseMapper.getListByMenuOrderNo(menuOrder.getMenuOrderNo());
        logger.info("MenuOrder menuOrderNo={},menuCourseList.size={}", menuOrder.getMenuOrderNo(), menuCourseList.size());
        menuOrderInfo.setCategoryList(getCategoryList(menuCourseList, merchantCourseCategoryList));
        return menuOrderInfo;
    }


    private List<MenuOrderCategory> getCategoryList(List<MenuCourse> menuCourseList, List<MerchantCourseCategory> merchantCourseCategoryList) {
        if (merchantCourseCategoryList.size() == 0) {
            List<MenuOrderCategory> menuOrderCategoryList = new ArrayList<>(1);
            MenuOrderCategory menuOrderCategory = new MenuOrderCategory();
            menuOrderCategory.setCategoryName("全部");
            List<MenuOrderCourseInfo> subList = new ArrayList<>(menuCourseList.size());
            menuCourseList.stream().forEach(item -> subList.add(getCourseInfo(item)));
            menuOrderCategory.setSubList(subList);
            menuOrderCategoryList.add(menuOrderCategory);
            return menuOrderCategoryList;
        } else {
            return getMenuOrderCategory(menuCourseList, merchantCourseCategoryList);
        }


    }


    private List<MenuOrderCategory> getMenuOrderCategory(List<MenuCourse> menuCourseList, List<MerchantCourseCategory> merchantCourseCategoryList) {
        List<MenuOrderCategory> menuOrderCategoryList = new ArrayList<>(merchantCourseCategoryList.size());
        for (MenuCourse course : menuCourseList) {
            boolean flag = false;
            for (MenuOrderCategory category : menuOrderCategoryList) {
                if (category.getCategoryName().equals(course.getMerchantCategoryName())) {
                    //标记flag＝true
                    flag = true;
                    //如果相等，则说明此course属于已有menuOrderCategoryList的分类中，直接加入
                    category.getSubList().add(getCourseInfo(course));
                    break;
                }
            }
            if (flag) {
                continue;
            }
            //如果没有，则新增MenuOrderCategory 并加入menuOrderCategoryList
            //1 新增MenuOrderCategory
            MenuOrderCategory menuOrderCategory = new MenuOrderCategory(course.getMerchantCategoryName());
            menuOrderCategory.getSubList().add(getCourseInfo(course));
            //加入menuOrderCategoryList
            menuOrderCategoryList.add(menuOrderCategory);
        }
        return menuOrderCategoryList;
    }

    private MenuOrderCourseInfo getCourseInfo(MenuCourse course) {
        MenuOrderCourseInfo courseInfo = new MenuOrderCourseInfo();
        courseInfo.setCourseName(course.getCourseName());
        courseInfo.setPrice(course.getPrice().intValue());
        courseInfo.setCourseId(course.getId());
        courseInfo.setCount(course.getCount());
        return courseInfo;
    }
}
