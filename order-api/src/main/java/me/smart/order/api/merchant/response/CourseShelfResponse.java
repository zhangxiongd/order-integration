package me.smart.order.api.merchant.response;

/**
 * Created by zhangxiong on 16/4/16.
 */
public class CourseShelfResponse {
    private String courseId;

    public CourseShelfResponse(String courseId) {
        this.courseId = courseId;
    }

    public CourseShelfResponse() {
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }
}
