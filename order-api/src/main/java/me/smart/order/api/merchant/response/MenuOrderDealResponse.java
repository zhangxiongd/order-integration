package me.smart.order.api.merchant.response;

import lombok.ToString;

/**
 * Created by zhangxiong on 16/4/5.
 */
@ToString
public class MenuOrderDealResponse {
    private String merchantId;
    private String menuOrderNo;

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getMenuOrderNo() {
        return menuOrderNo;
    }

    public void setMenuOrderNo(String menuOrderNo) {
        this.menuOrderNo = menuOrderNo;
    }
}
