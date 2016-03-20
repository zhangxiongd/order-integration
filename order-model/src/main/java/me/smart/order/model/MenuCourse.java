package me.smart.order.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by zhangxiong on 16/2/14.
 */
public class MenuCourse implements Serializable {
    private Long id;
    private Long courseId;
    private String outTradeNo;
    private Long merchantId;
    private Long memberId;
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

    public MenuCourse(Long courseId, String outTradeNo, Long merchantId,
                      Long memberId, BigDecimal salePrice, BigDecimal price,
                      Integer count) {
        this.courseId = courseId;
        this.outTradeNo = outTradeNo;
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

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
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
        final StringBuffer sb = new StringBuffer("MenuCourse{");
        sb.append("id=").append(id);
        sb.append(", courseId=").append(courseId);
        sb.append(", outTradeNo='").append(outTradeNo).append('\'');
        sb.append(", merchantId=").append(merchantId);
        sb.append(", memberId=").append(memberId);
        sb.append(", salePrice=").append(salePrice);
        sb.append(", price=").append(price);
        sb.append(", count=").append(count);
        sb.append(", isDelete=").append(isDelete);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", updatedAt=").append(updatedAt);
        sb.append('}');
        return sb.toString();
    }
}
