package me.smart.order.api.merchant.response;

import lombok.ToString;

import java.util.List;

/**
 * Created by zhangxiong on 16/4/4.
 */
@ToString
public class MenuOrderInfo {
    private String menuOrderNo;
    private String createdTime;
    private String tableNo;
    private String customerName;
    private int headerCount;
    private String totalAmount;
    private String remark;
    private int status;
    private String statusDesc;
    private List<MenuOrderCategory> categoryList;

    public String getMenuOrderNo() {
        return menuOrderNo;
    }

    public void setMenuOrderNo(String menuOrderNo) {
        this.menuOrderNo = menuOrderNo;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public String getTableNo() {
        return tableNo;
    }

    public void setTableNo(String tableNo) {
        this.tableNo = tableNo;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getHeaderCount() {
        return headerCount;
    }

    public void setHeaderCount(int headerCount) {
        this.headerCount = headerCount;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getStatusDesc() {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }

    public List<MenuOrderCategory> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<MenuOrderCategory> categoryList) {
        this.categoryList = categoryList;
    }
}
