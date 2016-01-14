package me.smart.order.api.Response;

import me.smart.order.api.CategoryInfo;

import java.util.List;

/**
 * Created by zhangxiong on 16/1/5.
 */
public class MerchantMenuResponse {
    private Long merchantId;
    private String merchantName;

    private List<CategoryInfo> categoryList;

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public List<CategoryInfo> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<CategoryInfo> categoryList) {
        this.categoryList = categoryList;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("MerchantMenuResponse{");
        sb.append("merchantId=").append(merchantId);
        sb.append(", merchantName='").append(merchantName).append('\'');
        sb.append(", categoryList=").append(categoryList);
        sb.append('}');
        return sb.toString();
    }
}
