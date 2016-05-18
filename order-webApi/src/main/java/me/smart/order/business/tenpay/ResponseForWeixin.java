package me.smart.order.business.tenpay;

import java.io.Serializable;

public class ResponseForWeixin implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 5133692390157129791L;

    private String return_code;

    private String return_msg;

    public ResponseForWeixin(String returnCode, String returnMsg) {
        this.return_code = returnCode;
        this.return_msg = returnMsg;
    }

    public String getReturn_code() {
        return return_code;
    }

    public void setReturn_code(String return_code) {
        this.return_code = return_code;
    }

    public String getReturn_msg() {
        return return_msg;
    }

    public void setReturn_msg(String return_msg) {
        this.return_msg = return_msg;
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("<xml>");
        buffer.append("<return_code><![CDATA[" + this.return_code + "]]></return_code>");
        buffer.append("<return_msg><![CDATA[" + this.return_msg + "]]></return_msg>");
        buffer.append("</xml>");
        return buffer.toString();

    }

}
