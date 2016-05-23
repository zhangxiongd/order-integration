package me.smart.order.dao;

import me.smart.order.model.CourseCategory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by zhangxiong on 15/12/31.
 */
public interface CourseCategoryMapper {
    List<CourseCategory> getCourseCategoryListByMerchantId(@Param("merchantId") Long merchantId);

    CourseCategory getById(@Param("id") Long id);

    CourseCategory getByMerchantIdAndName(@Param("merchantId") Long merchantId, @Param("categoryName") String name);

    /**
     * @param courseCategory
     * @return
     */
    Long insert(CourseCategory courseCategory);

    /**
     * 将分类置失效
     *
     * @param id
     * @return
     */
    int updateIsDelete(@Param("id") Long id);

    /**
     * 更新类名
     *
     * @return
     */
    int updateCategoryName(CourseCategory courseCategory);
}
