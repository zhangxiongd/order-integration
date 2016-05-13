package me.smart.order.api.merchant.response;

import java.util.List;

/**
 * Created by zhangxiong on 16/4/4.
 */
public class MenuOrderListResponse {
    private String merchantId;
    private String accountId;
    private List<MenuOrderInfo> orderList;

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public List<MenuOrderInfo> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<MenuOrderInfo> orderList) {
        this.orderList = orderList;
    }
}
