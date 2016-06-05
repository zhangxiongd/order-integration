package me.smart.order.business.tenpay;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;
import me.smart.order.constant.TenPayConstant;
import me.smart.order.dto.tenpay.RefundResData;
import me.smart.order.dto.tenpay.TenPayRefundReqData;
import me.smart.order.enums.ErrorCode;
import me.smart.order.exception.SystemException;
import me.smart.order.util.HttpCallService;
import me.smart.order.util.TenPaySignature;
import me.smart.order.util.Util;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by zhangxiong on 2016-05-26.
 */
@Component
public class TenPayRefundBusiness {

    Logger logger = LoggerFactory.getLogger(TenPayRefundBusiness.class);
    /**
     * 调用退款业务逻辑
     *
     * @param refundReqData 这个数据对象里面包含了API要求提交的各种数据字段
     * @throws Exception
     */
    public RefundResData process(TenPayRefundReqData refundReqData, String certPath, String password) throws Exception {

        String refundServiceResponseString = "";

        try {
            HttpCallService htthCallService = new HttpCallService();
            htthCallService.setHttpClient(certPath, password);
            XStream xStreamForRequestPostData = new XStream(new DomDriver("UTF-8", new XmlFriendlyNameCoder("-_", "_")));
            String postDataXML = xStreamForRequestPostData.toXML(refundReqData);
            logger.info("微信退款接口，post过去的数据为：" + postDataXML);
            long costTimeStart = System.currentTimeMillis();

            refundServiceResponseString = htthCallService.postXml(TenPayConstant.REFUND_API, postDataXML);
            logger.info("微信退款接口,-------------------------------------耗时：" + (System.currentTimeMillis() - costTimeStart));
        } catch (Exception e) {
            logger.error("申请退款请求失败,和微信通信失败");
            throw new SystemException(ErrorCode.HTTP_CONN_ERROR, ErrorCode.HTTP_CONN_ERROR.getDesc());

        }
        logger.info("退款申请API返回的数据如下：" + refundServiceResponseString);
        // refundServiceResponseString = null  时的容错处理
        if(StringUtils.isBlank(refundServiceResponseString)){
            logger.error("退款申请API返回的数据为空");
            throw new SystemException(ErrorCode.TENPAY_REFUND_RESULT_ERROR,ErrorCode.TENPAY_REFUND_RESULT_ERROR.getDesc());
        }
        //将从API返回的XML数据映射到Java对象
        RefundResData refundResData = (RefundResData) Util.getObjectFromXML(refundServiceResponseString,
                RefundResData.class);
        if (refundResData == null || refundResData.getReturn_code() == null) {
            logger.error("退款API请求逻辑错误，请仔细检测传过去的每一个参数是否合法，或是看API能否被正常访问");
            throw new SystemException(ErrorCode.TENPAY_SIGN_ERROR, ErrorCode.TENPAY_SIGN_ERROR.getDesc());
        }
        if (refundResData.getReturn_code().equals("FAIL")) {
            ///注意：一般这里返回FAIL是出现系统级参数错误，请检测Post给API的数据是否规范合法
            logger.error("退款API系统返回失败，请检测Post给API的数据是否规范合法");
            throw new SystemException(ErrorCode.TENPAY_REFUND_RESULT_ERROR, refundResData.getReturn_msg());

        } else {
            if (!TenPaySignature.checkResult(refundServiceResponseString)) {
                logger.error("微信退款请求API返回的数据签名验证失败，有可能数据被篡改了");
                throw new SystemException(ErrorCode.TENPAY_SIGN_ERROR, ErrorCode.TENPAY_SIGN_ERROR.getDesc());
            }
            if (refundResData.getResult_code().equals("FAIL")) {
                logger.error("微信退款请求出错，错误码：" + refundResData.getErr_code() + "     错误信息："
                        + refundResData.getErr_code_des());
                throw new SystemException(ErrorCode.TENPAY_REFUND_RESULT_ERROR, refundResData.getReturn_msg());

                //退款失败时再怎么延时查询退款状态都没有意义，这个时间建议要么再手动重试一次，依然失败的话请走投诉渠道进行投诉
            } else {
                return refundResData;
            }
        }
    }
}
