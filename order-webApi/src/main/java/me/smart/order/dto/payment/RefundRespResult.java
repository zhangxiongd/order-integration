package me.smart.order.dto.payment;

import lombok.ToString;

import java.io.Serializable;

@ToString
public class RefundRespResult implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -620863677389630658L;
    private String outTradNo;//商户订单号
    private int applyRefundStatus;//申请退款状态
    private String refundNo;//支付平台退款单号

    private String thirdRefundNo;//第三方退款单号

    public RefundRespResult() {

    }
    public String getOutTradNo() {
        return outTradNo;
    }

    public void setOutTradNo(String outTradNo) {
        this.outTradNo = outTradNo;
    }

    public int getApplyRefundStatus() {
        return applyRefundStatus;
    }

    public void setApplyRefundStatus(int applyRefundStatus) {
        this.applyRefundStatus = applyRefundStatus;
    }

    public String getRefundNo() {
        return refundNo;
    }

    public void setRefundNo(String refundNo) {
        this.refundNo = refundNo;
    }

    public String getThirdRefundNo() {
        return thirdRefundNo;
    }

    public void setThirdRefundNo(String thirdRefundNo) {
        this.thirdRefundNo = thirdRefundNo;
    }


}
