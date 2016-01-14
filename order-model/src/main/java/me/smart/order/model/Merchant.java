package me.smart.order.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by zhangxiong on 15/11/25.
 */
public class Merchant implements Serializable {
    private Long id;
    private String merchantName;
    private String shortName;
    private String password;
    private String merchantImg;
    private Integer merchantSort;
    private Boolean isDelete;
    private Date createdAt;
    private Date updatedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMerchantImg() {
        return merchantImg;
    }

    public void setMerchantImg(String merchantImg) {
        this.merchantImg = merchantImg;
    }

    public Integer getMerchantSort() {
        return merchantSort;
    }

    public void setMerchantSort(Integer merchantSort) {
        this.merchantSort = merchantSort;
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
        StringBuffer sb = new StringBuffer("Merchant{");

        sb.append("id=" + id);
        sb.append(", merchantName=" + merchantName);
        sb.append(", shortName=" + shortName);
        sb.append(", merchantSort=" + merchantSort);
        sb.append(", isDelete=" + isDelete);
        sb.append("}");
        return sb.toString();
    }
}
