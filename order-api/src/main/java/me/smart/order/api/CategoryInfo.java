package me.smart.order.api;

/**
 * Created by zhangxiong on 16/4/14.
 */
public class CategoryInfo {
    private Long categoryId;
    private String categoryName;


    public CategoryInfo() {
    }

    public CategoryInfo(Long categoryId, String categoryName) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
