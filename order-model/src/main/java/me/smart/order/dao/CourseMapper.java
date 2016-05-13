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

    Course findByMchIdAndCategoryIdAndName(@Param("merchantId") Long merchantId,
                                           @Param("categoryId") Long categoryId, @Param("name") String name);

    /**
     * 保存
     *
     * @param course
     * @return
     */
    int save(Course course);


    /**
     * 上下架操作
     *
     * @param course
     * @return
     */
    int updateByShelf(Course course);

    /**
     * 将菜置失效
     *
     * @param merchantId
     * @param categoryId
     * @return
     */
    int updateIsDeleteByCategory(@Param("merchantId") Long merchantId,
                                 @Param("categoryId") Long categoryId);

    /**
     * 更新价格 菜名  图像
     *
     * @param course
     * @return
     */
    int updateCourse(Course course);
}
