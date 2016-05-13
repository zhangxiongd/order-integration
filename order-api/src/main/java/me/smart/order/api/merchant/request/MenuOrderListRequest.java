package me.smart.order.api.merchant.request;

import lombok.ToString;
import me.smart.order.enums.ResultCode;
import me.smart.order.exception.BusinessException;

/**
 * Created by zhangxiong on 16/4/4.
 */
@ToString
public class MenuOrderListRequest extends BaseRequest {

    private String merchantId;
    private String accountId;
    /**
     * PENDING 1
     * PROCESS 4
     * SUCCESS 5
     */
    private Integer status;
    private Integer pageSize;
    private Integer pageNo;


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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    @Override
    public void validate() throws BusinessException {
        super.validate(sign, source, token);
        assertNotNull(merchantId, "餐厅ID", LENGTH_10);
        assertNotNull(accountId, "餐厅帐号ID", LENGTH_10);
        if (status == null || status == 0) {
            throw new BusinessException(ResultCode.REQUEST_NOT_VALID);
        }
        if (pageNo == null || pageNo == 0) {
            throw new BusinessException(ResultCode.REQUEST_NOT_VALID);
        }
        if (pageSize == null || pageNo == 0) {
            throw new BusinessException(ResultCode.REQUEST_NOT_VALID);
        }
    }
}
