package me.smart.order.api.merchant.request;

import lombok.ToString;
import me.smart.order.exception.BusinessException;

/**
 * Created by zhangxiong on 16/4/18.
 */
@ToString
public class MerchantCourseCategoryAddRequest extends BaseRequest {

    private String merchantId;
    private String accountId;
    private String categoryName;


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

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public void validate() throws BusinessException {
        super.validate(sign, source, token);
        assertNotNull(merchantId, "餐厅ID", LENGTH_10);
        assertNotNull(accountId, "餐厅帐号ID", LENGTH_10);
        assertNotNull(categoryName, "打印类别名", LENGTH_20);
    }
}
