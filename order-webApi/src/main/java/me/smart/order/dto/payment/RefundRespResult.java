package me.smart.order.dto.payment;

import java.io.Serializable;

public class RefundRespResult implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -620863677389630658L;
    private String out_trade_no;//商户订单号
    private int applyRefundStatus;//申请退款状态
    private String eleRefundNo;//支付平台退款单号

    private String thirdRefundNo;//第三方退款单号

    public RefundRespResult() {

    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public int getApplyRefundStatus() {
        return applyRefundStatus;
    }

    public void setApplyRefundStatus(int applyRefundStatus) {
        this.applyRefundStatus = applyRefundStatus;
    }

    public String getEleRefundNo() {
        return eleRefundNo;
    }

    public void setEleRefundNo(String eleRefundNo) {
        this.eleRefundNo = eleRefundNo;
    }

    public String getThirdRefundNo() {
        return thirdRefundNo;
    }

    public void setThirdRefundNo(String thirdRefundNo) {
        this.thirdRefundNo = thirdRefundNo;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("RefundRespResult [out_trade_no=");
        builder.append(out_trade_no);
        builder.append(", applyRefundStatus=");
        builder.append(applyRefundStatus);
        builder.append(", eleRefundNo=");
        builder.append(eleRefundNo);
        builder.append(", thirdRefundNo=");
        builder.append(thirdRefundNo);
        builder.append("]");
        return builder.toString();
    }

}
