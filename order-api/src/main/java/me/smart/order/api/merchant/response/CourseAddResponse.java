package me.smart.order.api.merchant.response;

/**
 * Created by zhangxiong on 16/4/15.
 */
public class CourseAddResponse {
    private String courseId;
    private String courseName;
    private String courseImg;
    private String price;

    public CourseAddResponse() {
    }

    public CourseAddResponse(String courseId, String courseName, String courseImg, String price) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.courseImg = courseImg;
        this.price = price;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseImg() {
        return courseImg;
    }

    public void setCourseImg(String courseImg) {
        this.courseImg = courseImg;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
