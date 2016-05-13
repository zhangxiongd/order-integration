package me.smart.order.api;

import lombok.ToString;

/**
 * Created by zhangxiong on 15/12/27.
 */
@ToString
public class CourseInfo {
    private Long courseId;
    private String courseName;
    //单位分
    private String price;
    //    private int specialPrice;
//    private int memberPrice;
    private String courseImg;
    //销量
    private int volume;

    //上架状态
    private int isShelf;

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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
//    public int getSpecialPrice() {
//        return specialPrice;
//    }
//
//    public void setSpecialPrice(int specialPrice) {
//        this.specialPrice = specialPrice;
//    }
//
//    public int getMemberPrice() {
//        return memberPrice;
//    }
//
//    public void setMemberPrice(int memberPrice) {
//        this.memberPrice = memberPrice;
//    }

    public String getCourseImg() {
        return courseImg;
    }

    public void setCourseImg(String courseImg) {
        this.courseImg = courseImg;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public int getIsShelf() {
        return isShelf;
    }

    public void setIsShelf(int isShelf) {
        this.isShelf = isShelf;
    }
}