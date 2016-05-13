package me.smart.order.api.merchant.request;

import me.smart.order.exception.BusinessException;

/**
 * Created by zhangxiong on 16/4/16.
 */
public class CategoryDeleteRequest extends BaseRequest {

    private String merchantId;
    private String accountId;
    private String categoryId;

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

    @Override
    public void validate() throws BusinessException {
        validateCommon();
        assertNotNull(merchantId, "餐厅ID", LENGTH_10);
        assertNotNull(accountId, "餐厅帐号ID", LENGTH_10);
        assertNotNull(categoryId, "分类ID", LENGTH_20);
    }
}
