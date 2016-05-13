package me.smart.order.api.merchant.response;

/**
 * Created by zhangxiong on 16/4/3.
 */
public class RegisterResponse {
    private String merchantId;
    private String accountId;

    public RegisterResponse() {
    }

    public RegisterResponse(String merchantId, String accountId) {
        this.merchantId = merchantId;
        this.accountId = accountId;
    }

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
}
