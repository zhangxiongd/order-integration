package me.smart.order.model;

import me.smart.order.enums.MenuOrderStatus;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 菜单订单表
 * Created by zhangxiong on 15/12/30.
 */
public class MenuOrder implements Serializable {
    private Long id;
    /**
     * 用户id
     */
    private Long memberId;
    /**
     * 餐厅id
     */
    private Long merchantId;
    /**
     * 桌号
     */
    private String tableNo;
    //订单号
    private String menuOrderNo;
    //订单总金额
    private BigDecimal totalAmount;
    //订单支付金额
    private BigDecimal payAmount;
    //订单优惠金额
    private BigDecimal discountAmount;
    //订单优惠信息
    private Integer discountType;
    //订单优惠纪录(不同的优惠方式纪录不同的信息)
    private String discountComment;
    //菜单订单状态 0:待处理,1:餐厅已接单,2:餐厅已拒单
    private Integer orderStatus;
    /**
     * 用户备注
     */
    private String remark;
    /**
     * 用餐人数
     */
    private Integer peopleNumber;


    private Boolean isDelete;


    private Date createdAt;

    private Date updatedAt;


    public MenuOrder() {
    }


    public MenuOrder(Long memberId, Long merchantId, String remark, String menuOrderNo,
                     BigDecimal totalAmount, BigDecimal payAmount, String tableNo, Integer peopleNumber) {
        this.memberId = memberId;
        this.merchantId = merchantId;
        this.remark = remark;
        this.menuOrderNo = menuOrderNo;
        this.totalAmount = totalAmount;
        this.payAmount = payAmount;
        this.tableNo = tableNo;
        this.orderStatus = MenuOrderStatus.PENDING.getStatus();
        this.isDelete = false;
        this.createdAt = new Date();
        this.updatedAt = new Date();
        this.peopleNumber = peopleNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public String getMenuOrderNo() {
        return menuOrderNo;
    }

    public void setMenuOrderNo(String menuOrderNo) {
        this.menuOrderNo = menuOrderNo;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }

    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }

    public Integer getDiscountType() {
        return discountType;
    }

    public void setDiscountType(Integer discountType) {
        this.discountType = discountType;
    }

    public String getDiscountComment() {
        return discountComment;
    }

    public void setDiscountComment(String discountComment) {
        this.discountComment = discountComment;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getTableNo() {
        return tableNo;
    }

    public void setTableNo(String tableNo) {
        this.tableNo = tableNo;
    }

    public Integer getPeopleNumber() {
        return peopleNumber;
    }

    public void setPeopleNumber(Integer peopleNumber) {
        this.peopleNumber = peopleNumber;
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
        final StringBuffer sb = new StringBuffer("MenuOrder{");
        sb.append("id=").append(id);
        sb.append(", memberId=").append(memberId);
        sb.append(", merchantId=").append(merchantId);
        sb.append(", menuOrderNo='").append(menuOrderNo);
        sb.append(", totalAmount=").append(totalAmount);
        sb.append(", payAmount=").append(payAmount);
        sb.append(", discountAmount=").append(discountAmount);
        sb.append(", discountType=").append(discountType);
        sb.append(", discountComment='").append(discountComment);
        sb.append(", orderStatus=").append(orderStatus);
        sb.append(", remark=").append(remark);
        sb.append(", tableNo=").append(tableNo);
        sb.append(", isDelete=").append(isDelete);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", updatedAt=").append(updatedAt);
        sb.append('}');
        return sb.toString();
    }
}
