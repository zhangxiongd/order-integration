package me.smart.order.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 菜系类别
 * Created by zhangxiong on 15/12/30.
 */
public class CourseCategory implements Serializable {
    private Long id;
    private String categoryName;
    private Long merchantId;
    private Integer sort;
    private Boolean isDelete;
    private Date createdAt;
    private Date updatedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
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

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("CourseCategory{");
        sb.append("id=").append(id);
        sb.append(", categoryName='").append(categoryName).append('\'');
        sb.append(", merchantId=").append(merchantId);
        sb.append(", sort=").append(sort);
        sb.append(", isDelete=").append(isDelete);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", updatedAt=").append(updatedAt);
        sb.append('}');
        return sb.toString();
    }
}
