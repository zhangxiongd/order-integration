package me.smart.order.api.merchant.request;

import me.smart.order.enums.ResultCode;
import me.smart.order.exception.BusinessException;

/**
 * Created by zhangxiong on 16/4/5.
 */
public class MenuOrderDealRequest extends BaseRequest {

    private String merchantId;
    private String accountId;
    private String menuOrderNo;
    /**
     * status = 4 打印订单
     * status ＝ 5 上菜
     */

    private Integer status;

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

    public String getMenuOrderNo() {
        return menuOrderNo;
    }

    public void setMenuOrderNo(String menuOrderNo) {
        this.menuOrderNo = menuOrderNo;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public void validate() throws BusinessException {
        validateCommon();
        assertNotNull(merchantId, "餐厅id", null);
        assertNotNull(accountId, "帐号id", null);
        assertNotNull(menuOrderNo, "订单号", null);
        if (status == null) {
            throw new BusinessException(ResultCode.REQUEST_NOT_VALID);
        }
    }
}
