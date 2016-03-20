package me.smart.order.dao;

import me.smart.order.model.Course;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by zhangxiong on 15/12/31.
 */
public interface CourseMapper {
    List<Course> getCourseListByMerchantAndCategory(@Param("merchantId") Long merchantId, @Param("categoryId") Long categoryId);

    Course findById(@Param("id") Long id);


    List<Course> findByIds(@Param("ids") Long[] ids);

}
