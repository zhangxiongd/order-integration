package me.smart.order.api;

import lombok.ToString;

import java.util.List;

/**
 * Created by zhangxiong on 16/1/5.
 */
@ToString
public class CategoryCourseInfo extends CategoryInfo {

    private List<CourseInfo> courseInfoList;

    public List<CourseInfo> getCourseInfoList() {
        return courseInfoList;
    }

    public void setCourseInfoList(List<CourseInfo> courseInfoList) {
        this.courseInfoList = courseInfoList;
    }
}
