package me.smart.order.dao;

import me.smart.order.model.CourseCategory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by zhangxiong on 15/12/31.
 */
public interface CourseCategoryMapper {
    List<CourseCategory> getCourseCategoryListByMerchantId(@Param("merchantId") Long merchantId);
}
