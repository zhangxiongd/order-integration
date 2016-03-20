package me.smart.order.api;

import lombok.ToString;

/**
 * Created by zhangxiong on 16/2/12.
 */
@ToString
public class MenuOrderCourseInfo {
    private Long courseId;
    private String courseName;
    private int price;
    private int count;

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

}
