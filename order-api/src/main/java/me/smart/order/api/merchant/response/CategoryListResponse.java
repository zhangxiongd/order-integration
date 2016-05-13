package me.smart.order.api.merchant.response;

import me.smart.order.api.CategoryInfo;

import java.util.List;

/**
 * Created by zhangxiong on 16/4/14.
 */
public class CategoryListResponse {
    private String merchantId;
    private String accountId;
    private List<CategoryInfo> categoryInfoList;


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

    public List<CategoryInfo> getCategoryInfoList() {
        return categoryInfoList;
    }

    public void setCategoryInfoList(List<CategoryInfo> categoryInfoList) {
        this.categoryInfoList = categoryInfoList;
    }
}
