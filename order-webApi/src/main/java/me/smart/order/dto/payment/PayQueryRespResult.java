package me.smart.order.dto.payment;

import java.io.Serializable;

public class PayQueryRespResult implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -3386223595976291940L;

    private String trade_type;//交易类型

    private String trade_state;//交易状态

    private String out_trade_no;//业务端发来的商户订单号

    private String transaction_id;//支付平台流水号

    private int total_fee;

    private String out_trans_id;//支付宝交易号

    //余额(查询所得余额)
    private int balance_amount;

    public PayQueryRespResult() {

    }

    public String getTrade_type() {
        return trade_type;
    }

    public void setTrade_type(String trade_type) {
        this.trade_type = trade_type;
    }

    public String getTrade_state() {
        return trade_state;
    }

    public void setTrade_state(String trade_state) {
        this.trade_state = trade_state;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public String getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }

    public int getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(int total_fee) {
        this.total_fee = total_fee;
    }

    public String getOut_trans_id() {
        return out_trans_id;
    }

    public void setOut_trans_id(String out_trans_id) {
        this.out_trans_id = out_trans_id;
    }

    public int getBalance_amount() {
        return balance_amount;
    }

    public void setBalance_amount(int balance_amount) {
        this.balance_amount = balance_amount;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("PayQueryRespResult [trade_type=");
        builder.append(trade_type);
        builder.append(", trade_state=");
        builder.append(trade_state);
        builder.append(", out_trade_no=");
        builder.append(out_trade_no);
        builder.append(", transaction_id=");
        builder.append(transaction_id);
        builder.append(", total_fee=");
        builder.append(total_fee);
        builder.append(", out_trans_id=");
        builder.append(out_trans_id);
        builder.append(", balance_amount=");
        builder.append(balance_amount);
        builder.append("]");
        return builder.toString();
    }

}
