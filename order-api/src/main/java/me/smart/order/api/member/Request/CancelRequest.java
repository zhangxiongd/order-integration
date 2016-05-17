package me.smart.order.api.member.Request;

import lombok.ToString;
import me.smart.order.exception.BusinessException;

/**
 * Created by zhangxiong on 16/3/28.
 */
@ToString
public class CancelRequest extends BaseRequest {

    //商家id
    private String merchantId;
    //用户ID
    private String memberId;
    //菜单订单号
    private String menuOrderNo;

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

    public String getMenuOrderNo() {
        return menuOrderNo;
    }

    public void setMenuOrderNo(String menuOrderNo) {
        this.menuOrderNo = menuOrderNo;
    }

    @Override
    public void validate() throws BusinessException {
        assertNotNull(merchantId, "餐厅ID", LENGTH_10);
        assertNotNull(memberId, "用户ID", LENGTH_10);
        assertNotNull(menuOrderNo, "菜单订单号", null);
    }
}
