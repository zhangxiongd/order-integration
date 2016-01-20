package me.smart.order.api;

/**
 * Created by zhangxiong on 15/12/27.
 */
public class CourseInfo {
    private Long courseId;
    private String courseName;
    //单位分
    private int salePrice;
    private int specialPrice;
    private int memberPrice;
    private String courseImg;
    //销量
    private int volume;

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

    public int getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(int salePrice) {
        this.salePrice = salePrice;
    }

    public int getSpecialPrice() {
        return specialPrice;
    }

    public void setSpecialPrice(int specialPrice) {
        this.specialPrice = specialPrice;
    }

    public int getMemberPrice() {
        return memberPrice;
    }

    public void setMemberPrice(int memberPrice) {
        this.memberPrice = memberPrice;
    }

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

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("CourseInfo{");
        sb.append("courseId=").append(courseId);
        sb.append(", courseName='").append(courseName).append('\'');
        sb.append(", salePrice=").append(salePrice);
        sb.append(", specialPrice=").append(specialPrice);
        sb.append(", memberPrice=").append(memberPrice);
        sb.append(", courseImg='").append(courseImg).append('\'');
        sb.append(", volume=").append(volume);
        sb.append('}');
        return sb.toString();
    }

}