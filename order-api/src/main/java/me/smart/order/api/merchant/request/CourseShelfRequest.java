package me.smart.order.api.merchant.request;

import me.smart.order.exception.BusinessException;

/**
 * Created by zhangxiong on 16/4/16.
 */
public class CourseShelfRequest extends BaseRequest {

    private String merchantId;
    private String accountId;
    private String courseId;
    /**
     * type=0 上架
     * type=1 下架
     */
    private int type;

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

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public void validate() throws BusinessException {
        validateCommon();
        assertNotNull(merchantId, "餐厅ID", LENGTH_10);
        assertNotNull(accountId, "餐厅帐号ID", LENGTH_10);
        assertNotNull(courseId, "餐厅菜品ID", LENGTH_10);

    }
}
