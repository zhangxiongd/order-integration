package me.smart.order.business.tenpay;

import me.smart.order.constant.TenPayConstant;
import me.smart.order.dto.tenpay.UnifiedOrderReqData;
import me.smart.order.dto.tenpay.UnifiedOrderRespData;
import me.smart.order.enums.ErrorCode;
import me.smart.order.exception.SystemException;
import me.smart.order.util.HttpCallService;
import me.smart.order.util.TenPaySignature;
import me.smart.order.util.XMLParser;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by zhangxiong on 16/3/19.
 */
@Component
public class TenPayUnifiedOrderBusiness {
    private Logger logger = LoggerFactory.getLogger(TenPayUnifiedOrderBusiness.class);


    /**
     * 向微信发起统一下单请求
     *
     * @param unifiedOrderReqData
     * @return
     */
    public UnifiedOrderRespData process(UnifiedOrderReqData unifiedOrderReqData) throws Exception {
        String result = "";
        String xmlPostData = XMLParser.objectToXml(unifiedOrderReqData);
        logger.info("微信统一下单参数：xmlPostData={}", xmlPostData);
        HttpCallService httpCallService = new HttpCallService();
        long startTime = System.currentTimeMillis();
        try {
            result = httpCallService.postXml(TenPayConstant.UNIFIED_ORDER_API, xmlPostData);
            logger.info("微信统一下单耗时,cost={}", System.currentTimeMillis() - startTime);
        } catch (Exception e) {
            logger.error("微信统一下单失败,cost={}", System.currentTimeMillis() - startTime);
            logger.error(e.getMessage(), e);
        }

        logger.info("微信统一下单API返回结果,result={}", result);
        if (StringUtils.isBlank(result)) {
            throw new SystemException(ErrorCode.TENPAY_SYS_ERROR, ErrorCode.TENPAY_SYS_ERROR.getDesc());
        }
        //xml convert to object
        UnifiedOrderRespData respData = (UnifiedOrderRespData) XMLParser.xmlToMap(result);
        if (respData == null || respData.getReturn_code() == null) {
            logger.error("统一下单API请求逻辑错误，请仔细检测传过去的每一个参数是否合法，或是看API能否被正常访问");
            throw new SystemException(ErrorCode.TENPAY_SYS_ERROR, ErrorCode.TENPAY_SYS_ERROR.getDesc());
        }

        if (respData.getReturn_code().equals("FAIL")) {
            logger.error("统一下单API系统返回失败，请检测Post给API的数据是否规范合法");
            throw new SystemException(ErrorCode.TENPAY_PAY_RESULT_ERROR, respData.getReturn_msg());

        } else {
            logger.info("统一下单API系统成功返回数据");
            if (!TenPaySignature.checkResult(result)) {
                logger.error("统一下单请求API返回的数据签名验证失败，有可能数据被篡改了");
                throw new SystemException(ErrorCode.TENPAY_SIGN_ERROR, ErrorCode.TENPAY_SIGN_ERROR.getDesc());
            }
            if (respData.getResult_code().equals("FAIL")) {
                logger.error("出错，错误码：" + respData.getErr_code() + " 错误信息："
                        + respData.getErr_code_des());
                throw new SystemException(ErrorCode.TENPAY_PAY_RESULT_ERROR, respData.getErr_code_des());
            }
            return respData;

        }
    }
}
