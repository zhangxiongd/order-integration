package me.smart.order.api.member.Response;

import lombok.ToString;
import me.smart.order.api.CategoryCourseInfo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zhangxiong on 16/1/5.
 */
@ToString
public class MerchantMenuResponse implements Serializable {
    private Long merchantId;
    private String merchantName;

    private List<CategoryCourseInfo> categoryList;

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

    public List<CategoryCourseInfo> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<CategoryCourseInfo> categoryList) {
        this.categoryList = categoryList;
    }

}
