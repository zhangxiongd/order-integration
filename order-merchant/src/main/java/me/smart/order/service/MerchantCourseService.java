package me.smart.order.service;

import me.smart.order.api.Result;
import me.smart.order.api.merchant.request.*;
import me.smart.order.api.merchant.response.CategoryListResponse;

/**
 * Created by zhangxiong on 16/4/14.
 */
public interface MerchantCourseService {
    Result<CategoryListResponse> listCategory(CategoryListRequest request) throws Exception;

    Result addCategory(CategoryAddRequest request) throws Exception;

    Result deleteCategory(CategoryDeleteRequest request) throws Exception;


    Result updateCategory(CategoryUpdateRequest request) throws Exception;

    Result addCourse(CourseAddRequest request) throws Exception;

    Result deleteCourse() throws Exception;

    Result updateCourse(CourseUpdateRequest request) throws Exception;

    Result listCourseByCategory(CourseListRequest request) throws Exception;


    Result shelf(CourseShelfRequest request) throws Exception;
}
