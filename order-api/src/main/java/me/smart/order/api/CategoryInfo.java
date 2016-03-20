package me.smart.order.api;

import lombok.ToString;

import java.util.List;

/**
 * Created by zhangxiong on 16/1/5.
 */
@ToString
public class CategoryInfo {
    private Long categoryId;
    private String categoryName;
    private List<CourseInfo> courseInfoList;

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<CourseInfo> getCourseInfoList() {
        return courseInfoList;
    }

    public void setCourseInfoList(List<CourseInfo> courseInfoList) {
        this.courseInfoList = courseInfoList;
    }
}
