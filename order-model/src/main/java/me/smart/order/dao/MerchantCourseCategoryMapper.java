package me.smart.order.dao;

import me.smart.order.model.MerchantCourseCategory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by zhangxiong on 16/3/28.
 */
public interface MerchantCourseCategoryMapper {

    List<MerchantCourseCategory> getListByMerchantId(@Param("merchantId") Long merchantId);

    int insert(MerchantCourseCategory merchantCourseCategory);

    MerchantCourseCategory findById(@Param("id") Long id);

    int delete(@Param("id") Long id);

    int update(MerchantCourseCategory merchantCourseCategory);


    MerchantCourseCategory findByMchIdAndName(@Param("merchantId") Long merchantId, @Param("categoryName") String categoryName);
}
