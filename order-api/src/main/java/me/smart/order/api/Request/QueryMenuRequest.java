package me.smart.order.api.Request;

import lombok.ToString;
import me.smart.order.exception.BusinessException;

/**
 * Created by zhangxiong on 16/3/12.
 */
@ToString
public class QueryMenuRequest extends BaseRequest {
    private String merchantId;
    private String memberId;

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    @Override
    public void validate() throws BusinessException {
        assertNotNull(merchantId, "商户id", LENGTH_10);
//        assertNotNull(memberId, "用户id", LENGTH_10);
    }
}
