package me.smart.order.api.Response;

import lombok.ToString;
import me.smart.order.api.MenuOrderCourseInfo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zhangxiong on 16/3/13.
 */
@ToString
public class MenuOrderResponse implements Serializable {
    private String menuOrderNo;
    private String tableNo;
    private String merchantName;
    private String totalAmount;
    private String orderTime;
    private String orderStatusDesc;
    private Integer orderStatus;
    private String remark;

    private List<MenuOrderCourseInfo> menuOrderCourseInfoList;

    public String getMenuOrderNo() {
        return menuOrderNo;
    }

    public void setMenuOrderNo(String menuOrderNo) {
        this.menuOrderNo = menuOrderNo;
    }

    public String getTableNo() {
        return tableNo;
    }

    public void setTableNo(String tableNo) {
        this.tableNo = tableNo;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public String getOrderStatusDesc() {
        return orderStatusDesc;
    }

    public void setOrderStatusDesc(String orderStatusDesc) {
        this.orderStatusDesc = orderStatusDesc;
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

    public List<MenuOrderCourseInfo> getMenuOrderCourseInfoList() {
        return menuOrderCourseInfoList;
    }

    public void setMenuOrderCourseInfoList(List<MenuOrderCourseInfo> menuOrderCourseInfoList) {
        this.menuOrderCourseInfoList = menuOrderCourseInfoList;
    }
}
