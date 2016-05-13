package me.smart.order.api.member.Request;

import lombok.ToString;
import me.smart.order.api.MenuOrderCourseInfo;
import me.smart.order.enums.ResultCode;
import me.smart.order.exception.BusinessException;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zhangxiong on 16/2/2.
 */
@ToString
public class MenuOrderRequest extends BaseRequest implements Serializable {
    //下单会员id
    private String memberId;
    //商户ID
    private String merchantId;
    //总金额 单位分
    private int totalAmount;
    //餐桌号
    private String tableNo;
    //用户备注
    private String remark;

    /**
     * 用餐人数
     */
    private int headerCount;

    //菜单订单菜品
    private List<MenuOrderCourseInfo> menuOrderCourseInfoList;

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getTableNo() {
        return tableNo;
    }

    public void setTableNo(String tableNo) {
        this.tableNo = tableNo;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<MenuOrderCourseInfo> getMenuOrderCourseInfoList() {
        return menuOrderCourseInfoList;
    }

    public void setMenuOrderCourseInfoList(List<MenuOrderCourseInfo> menuOrderCourseInfoList) {
        this.menuOrderCourseInfoList = menuOrderCourseInfoList;
    }


    public int getHeaderCount() {
        return headerCount;
    }

    public void setHeaderCount(int headerCount) {
        this.headerCount = headerCount;
    }

    @Override
    public void validate() throws BusinessException {
        if (totalAmount <= 0) {
            throw new BusinessException(ResultCode.REQUEST_NOT_VALID, "订单金额无效");
        }
        if (menuOrderCourseInfoList != null || menuOrderCourseInfoList.size() == 0) {
            throw new BusinessException(ResultCode.REQUEST_NOT_VALID, "订单信息错误");
        }
        if (headerCount == 0) {
            throw new BusinessException(ResultCode.REQUEST_NOT_VALID, "用餐人数为空");
        }
        assertNotNull(merchantId, "用户id", LENGTH_10);
        assertNotNull(merchantId, "商户id", LENGTH_10);
        assertNotNull(tableNo, "桌号", LENGTH_10);
        assertNotNull(sign, "签名", null);
        int amounts = menuOrderCourseInfoList.stream().mapToInt(item -> item.getPrice()).sum();
        if (totalAmount != amounts) {
            throw new BusinessException(ResultCode.REQUEST_NOT_VALID, "订单金额校验错误");
        }


    }

}
