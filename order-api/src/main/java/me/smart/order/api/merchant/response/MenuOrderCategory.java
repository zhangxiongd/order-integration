package me.smart.order.api.merchant.response;

import lombok.ToString;
import me.smart.order.api.MenuOrderCourseInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangxiong on 16/4/4.
 */
@ToString
public class MenuOrderCategory {
    private String categoryName;
    private List<MenuOrderCourseInfo> subList;

    public MenuOrderCategory() {
    }

    public MenuOrderCategory(String categoryName) {
        this.categoryName = categoryName;
        this.subList = new ArrayList<>();
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<MenuOrderCourseInfo> getSubList() {
        return subList;
    }

    public void setSubList(List<MenuOrderCourseInfo> subList) {
        this.subList = subList;
    }
}
