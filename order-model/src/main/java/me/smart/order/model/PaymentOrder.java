package me.smart.order.model;

import lombok.ToString;
import me.smart.order.enums.PaymentOrderStatus;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 支付订单表
 * Created by zhangxiong on 15/12/30.
 */
@ToString
public class PaymentOrder implements Serializable {
    private Long id;
    private Long merchantId;
    private Long memberId;
    //订单号
    private String outTradeNo;
    //菜单订单号
    private String menuOrderNo;
    //支付订单总金额
    private BigDecimal totalAmount;

    private String orderName;

    //订单描述
    private String productBody;
    //订单附加信息
    private String orderData;

    //订单超时时间 单位秒
    private Integer payDeadLine;

    //支付货币方式
    private String currency;

    //订单状态
    private Integer orderStatus;

    private Boolean isDelete;

    private String returnCode;

    private String returnMsg;

    private Date createdAt;
    private Date updatedAt;

    public PaymentOrder() {
    }


    public PaymentOrder(Long merchantId, Long memberId,
                        String outTradeNo, String menuOrderNo,
                        BigDecimal totalAmount, String productBody, Integer payDeadLine) {
        this.merchantId = merchantId;
        this.memberId = memberId;
        this.outTradeNo = outTradeNo;
        this.menuOrderNo = menuOrderNo;
        this.totalAmount = totalAmount;
        this.productBody = productBody;
        this.payDeadLine = payDeadLine;
        this.currency = "CNY";
        this.orderStatus = PaymentOrderStatus.PROCESS.getStatus();
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

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
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

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getProductBody() {
        return productBody;
    }

    public void setProductBody(String productBody) {
        this.productBody = productBody;
    }

    public String getOrderData() {
        return orderData;
    }

    public void setOrderData(String orderData) {
        this.orderData = orderData;
    }

    public Integer getPayDeadLine() {
        return payDeadLine;
    }

    public void setPayDeadLine(Integer payDeadLine) {
        this.payDeadLine = payDeadLine;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public String getReturnMsg() {
        return returnMsg;
    }

    public void setReturnMsg(String returnMsg) {
        this.returnMsg = returnMsg;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

}
