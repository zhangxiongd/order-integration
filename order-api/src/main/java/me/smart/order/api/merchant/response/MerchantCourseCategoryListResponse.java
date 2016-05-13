package me.smart.order.api.merchant.response;

/**
 * Created by zhangxiong on 16/5/2.
 */
public class MerchantCourseCategoryListResponse {
    private String categoryId;
    private String categoryName;

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
