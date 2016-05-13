package me.smart.order.model;

import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 菜品
 * Created by zhangxiong on 15/12/29.
 */
@ToString
public class Course implements Serializable {

    public static Integer DEFAULT_SORT = 100;

    private Long id;

    //菜名
    private String name;

    //所属商户
    private Long merchantId;

    //所属分类
    private Long categoryId;


    //图像地址
    private String courseImg;

    //售价 分
    private BigDecimal salePrice;

    //特价
    private BigDecimal specialPrice;

    //会员价
    private BigDecimal memberPrice;

    //是否上下架
    private Boolean isShelf;

    private Integer sort;


    //是否有效
    private Boolean isDelete;

    private Date createdAt;

    private Date updatedAt;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCourseImg() {
        return courseImg;
    }

    public void setCourseImg(String courseImg) {
        this.courseImg = courseImg;
    }

    public BigDecimal getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(BigDecimal salePrice) {
        this.salePrice = salePrice;
    }

    public BigDecimal getSpecialPrice() {
        return specialPrice;
    }

    public void setSpecialPrice(BigDecimal specialPrice) {
        this.specialPrice = specialPrice;
    }

    public BigDecimal getMemberPrice() {
        return memberPrice;
    }

    public void setMemberPrice(BigDecimal memberPrice) {
        this.memberPrice = memberPrice;
    }

    public Boolean getIsShelf() {
        return isShelf;
    }

    public void setIsShelf(Boolean isShelf) {
        this.isShelf = isShelf;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
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

}
