package me.smart.order.model;

import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by zhangxiong on 16/2/14.
 */
@ToString
public class MenuCourse implements Serializable {
    private Long id;
    private Long courseId;
    private String courseName;
    private String menuOrderNo;
    private Long merchantCategoryId;
    private String merchantCategoryName;
    private Long memberId;
    private Long merchantId;
    //销售价格快照
    private BigDecimal salePrice;
    //实际价格快照
    private BigDecimal price;
    //数量
    private Integer count;

    private Boolean isDelete;

    private Date createdAt;

    private Date updatedAt;

    public MenuCourse() {
    }

    public MenuCourse(Long courseId, String menuOrderNo, Long merchantId,
                      Long memberId, BigDecimal salePrice, BigDecimal price,
                      Integer count) {
        this.courseId = courseId;
        this.menuOrderNo = menuOrderNo;
        this.merchantId = merchantId;
        this.memberId = memberId;
        this.salePrice = salePrice;
        this.price = price;
        this.count = count;
        this.isDelete = false;
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getMenuOrderNo() {
        return menuOrderNo;
    }

    public void setMenuOrderNo(String menuOrderNo) {
        this.menuOrderNo = menuOrderNo;
    }

    public Long getMerchantCategoryId() {
        return merchantCategoryId;
    }

    public void setMerchantCategoryId(Long merchantCategoryId) {
        this.merchantCategoryId = merchantCategoryId;
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public BigDecimal getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(BigDecimal salePrice) {
        this.salePrice = salePrice;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getMerchantCategoryName() {
        return merchantCategoryName;
    }

    public void setMerchantCategoryName(String merchantCategoryName) {
        this.merchantCategoryName = merchantCategoryName;
    }
}
