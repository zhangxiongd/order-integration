package me.smart.order.constant;

import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;
import me.smart.order.util.JsonConvertUtils;

import java.util.Map;

/**
 * Created by zhangxiong on 16/3/31.
 */
public class AliMessageConstant {
    public static final String APP_KEY = "23335521";
    public static final String APP_SECRET = "4c3f778c5c709bdcb7005bd01ebc6eb1";
    public static final String MESSAGE_URL = "http://gw.api.taobao.com/router/rest";
    public static final String MESSAGE_TEMPLATE_CODE = "SMS_7150203";

    public static boolean sendMessage(String mobile, String verifyCode) {
        TaobaoClient client = new DefaultTaobaoClient(MESSAGE_URL, APP_KEY, APP_SECRET);
        AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
        req.setSmsType("normal");
        req.setSmsFreeSignName("注册验证");
        req.setSmsTemplateCode(MESSAGE_TEMPLATE_CODE);
        req.setSmsParamString("{\"code\":\"" + verifyCode + "\",\"product\":\"点米生活\"}");
        req.setRecNum(mobile);
        AlibabaAliqinFcSmsNumSendResponse rsp = null;
        try {
            rsp = client.execute(req);
            Map<String, Object> map = JsonConvertUtils.convertToMap(rsp.getBody());
            if (map.get("alibaba_aliqin_fc_sms_num_send_response") != null) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }
}
