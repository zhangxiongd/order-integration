package me.smart.order.dto.tenpay;


import lombok.ToString;
import me.smart.order.dto.payment.PayRespResult;

@ToString
public class TenPayResResult extends PayRespResult {

    private static final long serialVersionUID = 6133273539323431703L;
    private String trade_type;
    private String prepay_id;
    private String code_url;

    public TenPayResResult() {

    }

    public String getTrade_type() {
        return trade_type;
    }

    public void setTrade_type(String trade_type) {
        this.trade_type = trade_type;
    }

    public String getPrepay_id() {
        return prepay_id;
    }

    public void setPrepay_id(String prepay_id) {
        this.prepay_id = prepay_id;
    }

    public String getCode_url() {
        return code_url;
    }

    public void setCode_url(String code_url) {
        this.code_url = code_url;
    }

}
