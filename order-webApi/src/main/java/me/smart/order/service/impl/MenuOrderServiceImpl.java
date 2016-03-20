package me.smart.order.service.impl;

import me.smart.order.api.MenuOrderCourseInfo;
import me.smart.order.api.Request.MenuOrderRequest;
import me.smart.order.dao.CourseMapper;
import me.smart.order.dao.MenuCourseMapper;
import me.smart.order.dao.MenuOrderMapper;
import me.smart.order.enums.ResultCode;
import me.smart.order.exception.BusinessException;
import me.smart.order.model.Course;
import me.smart.order.model.MenuCourse;
import me.smart.order.model.MenuOrder;
import me.smart.order.service.MenuOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by zhangxiong on 16/2/14.
 */
@Service("menuOrderService")
@Transactional(readOnly = true)
public class MenuOrderServiceImpl implements MenuOrderService {
    private Logger logger = LoggerFactory.getLogger(MenuOrderServiceImpl.class);
    @Resource
    private CourseMapper courseMapper;
    @Resource
    private MenuCourseMapper menuCourseMapper;
    @Resource
    private MenuOrderMapper menuOrderMapper;

    /**
     * 根据菜品订单 下菜单＋支付订单＋请求第三方支付
     *
     * @param menuOrderRequest
     * @return
     * @throws BusinessException
     */
    @Transactional
    public MenuOrder transMenuOrder(MenuOrderRequest menuOrderRequest) throws BusinessException {

        //先落地菜单订单
        logger.info("MenuOrderServiceImpl transMenuOrder 请求参数:menuOrderRequest={}", menuOrderRequest);
        List<MenuOrderCourseInfo> menuOrderCourseInfoList = menuOrderRequest.getMenuOrderCourseInfoList();
        logger.info("用户点单菜品数量 count={}", menuOrderCourseInfoList.size());
        BigDecimal totalAmount = null;
        //todo..生成订单号
        String menuOrderNo = "";
        logger.info("落地菜品订单包含的菜品开始。。menuOrder={}", menuOrderNo);
        menuOrderCourseInfoList.stream().forEach(menuOrderCourseInfo -> {
            Course course = courseMapper.findById(menuOrderCourseInfo.getCourseId());
            /**
             * 将菜名设置到menuOrderCourseInfo中，controller中将其直接传给前端
             */
            menuOrderCourseInfo.setCourseName(course.getName());
            totalAmount.add(course.getSalePrice());
            MenuCourse menuCourse = new MenuCourse(course.getId(), menuOrderNo, Long.valueOf(menuOrderRequest.getMerchantId()),
                    Long.valueOf(menuOrderRequest.getMemberId()), course.getSalePrice(), course.getSalePrice(), menuOrderCourseInfo.getCount());
            menuCourseMapper.insert(menuCourse);
        });
        logger.info("落地菜品订单包含的菜品结束。。");
        if (totalAmount.intValue() != menuOrderRequest.getTotalAmount()) {
            throw new BusinessException(ResultCode.REQUEST_NOT_VALID, "订单金额校验错误");
        }
        MenuOrder menuOrder = new MenuOrder(Long.valueOf(menuOrderRequest.getMemberId()),
                Long.valueOf(menuOrderRequest.getMerchantId()), menuOrderRequest.getRemark(), menuOrderNo,
                totalAmount, totalAmount, menuOrderRequest.getTableNo(), menuOrderRequest.getPeopleNumber());
        logger.info("落地菜订单, menuOrder = {}", menuOrder);
        menuOrderMapper.insert(menuOrder);
        logger.info("落地菜订单结束, menuOrder = {}", menuOrder);
        return menuOrder;
    }
}
