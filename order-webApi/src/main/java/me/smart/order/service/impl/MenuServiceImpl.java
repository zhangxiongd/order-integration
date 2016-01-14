package me.smart.order.service.impl;

import me.smart.order.api.CategoryInfo;
import me.smart.order.api.CourseInfo;
import me.smart.order.api.Response.MerchantMenuResponse;
import me.smart.order.dao.CourseCategoryMapper;
import me.smart.order.dao.CourseMapper;
import me.smart.order.dao.MerchantMapper;
import me.smart.order.enums.ResultCode;
import me.smart.order.exception.BusinessException;
import me.smart.order.model.Course;
import me.smart.order.model.CourseCategory;
import me.smart.order.model.Merchant;
import me.smart.order.service.MenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangxiong on 16/1/5.
 */
@Service("menuService")
public class MenuServiceImpl implements MenuService {

    private Logger logger = LoggerFactory.getLogger(MenuServiceImpl.class);
    @Resource
    private CourseCategoryMapper courseCategoryMapper;
    @Resource
    private CourseMapper courseMapper;
    @Resource
    private MerchantMapper merchantMapper;

    /**
     * 获取merchantId下的菜单信息
     *
     * @param merchantId
     * @return
     */
    public MerchantMenuResponse getMerchantMenuList(Long merchantId) throws BusinessException {
        logger.info("MenuServiceImpl.getMerchantMenuList 根据merchantId获取此餐厅的菜单,merchantId={}", merchantId);
        Merchant merchant = merchantMapper.getMerchantById(merchantId);
        if (merchant == null) {
            throw new BusinessException(ResultCode.MERCHANT_ERROR);
        }
        //首先获取此餐厅的菜类别
        List<CourseCategory> categoryListFromDB = courseCategoryMapper.getCourseCategoryListByMerchantId(merchantId);
        logger.info("获取merchantId=1的菜单类别列表,categoryList={}", categoryListFromDB);
        MerchantMenuResponse result = new MerchantMenuResponse();
        result.setMerchantId(merchantId);
        result.setMerchantName(merchant.getMerchantName());

        List<CourseInfo> courseInfoList = new ArrayList<CourseInfo>();
        List<CategoryInfo> categoryInfoList = new ArrayList<CategoryInfo>(categoryListFromDB.size());
        CourseInfo courseInfo = null;
        List<Course> courseList = null;
        CategoryInfo categoryInfo = null;
        for (CourseCategory category : categoryListFromDB) {
            categoryInfo = new CategoryInfo();
            categoryInfo.setCategoryId(category.getId());
            categoryInfo.setCategoryName(category.getCategoryName());
            courseList = courseMapper.getCourseListByMerchantAndCategory(merchantId, category.getId());
            for (Course course : courseList) {
                courseInfo = new CourseInfo();
                courseInfo.setCourseId(course.getId());
                courseInfo.setCourseName(course.getName());
                courseInfo.setCourseImg(course.getCourseImg());
                courseInfo.setMemberPrice(course.getMemberPrice().intValue());
                courseInfo.setSalePrice(course.getSalePrice().intValue());
                courseInfo.setSpecialPrice(course.getSpecialPrice().intValue());
                //todo 设置销量
//                courseInfo.setVolume();
                courseInfoList.add(courseInfo);
            }
            categoryInfo.setCourseInfoList(courseInfoList);
            categoryInfoList.add(categoryInfo);
        }
        result.setCategoryList(categoryInfoList);
        return result;
    }
}
