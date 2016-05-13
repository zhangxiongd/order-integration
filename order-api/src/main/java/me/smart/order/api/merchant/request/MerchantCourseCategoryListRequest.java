package me.smart.order.api.merchant.request;

import me.smart.order.exception.BusinessException;

/**
 * Created by zhangxiong on 16/5/2.
 */
public class MerchantCourseCategoryListRequest extends BaseRequest {

    private String merchantId;
    private String accountId;


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

    @Override
    public void validate() throws BusinessException {
        validateCommon();
        assertNotNull(merchantId, "餐厅ID", LENGTH_10);
        assertNotNull(accountId, "餐厅帐号ID", LENGTH_10);
    }
}
