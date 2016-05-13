package me.smart.order.api.merchant.request;

import me.smart.order.exception.BusinessException;

/**
 * Created by zhangxiong on 16/4/15.
 */
public class CourseAddRequest extends BaseRequest {

    private String merchantId;
    private String accountId;
    private String categoryId;
    private String courseName;
    /**
     * 菜价格 元 字符串，需要转化成int存入数据库
     */
    private String price;

    private String courseImg;

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

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCourseImg() {
        return courseImg;
    }

    public void setCourseImg(String courseImg) {
        this.courseImg = courseImg;
    }

    @Override

    public void validate() throws BusinessException {
        validateCommon();
        assertNotNull(merchantId, "餐厅ID", LENGTH_10);
        assertNotNull(accountId, "餐厅帐户ID", LENGTH_10);
        assertNotNull(categoryId, "分类ID", LENGTH_10);
        assertNotNull(courseName, "菜品名", LENGTH_10);
        assertNotNull(price, "菜品价格", null);

    }
}
