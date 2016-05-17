package me.smart.order.notify.impl;

import me.smart.order.business.tenpay.NotifyRespData;
import me.smart.order.dao.PaymentRecordMapper;
import me.smart.order.enums.ErrorCode;
import me.smart.order.enums.ResultCode;
import me.smart.order.exception.BusinessException;
import me.smart.order.exception.SystemException;
import me.smart.order.model.PaymentRecord;
import me.smart.order.notify.BaseNotifyService;
import me.smart.order.util.Util;
import me.smart.order.weixin.WeixinSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by zhangxiong on 16/5/16.
 */
@Component
public class AcceptTenpayNotify implements BaseNotifyService {

    private Logger logger = LoggerFactory.getLogger(AcceptTenpayNotify.class);

    @Resource
    private PaymentRecordMapper paymentRecordMapper;

    @Override
    public PaymentRecord parse(String msg) throws Exception {
        logger.info("AcceptTenPayNotify parse param={}", msg);
        NotifyRespData notifyRespData = msgCheck(msg);
        PaymentRecord paymentRecord = paymentRecordMapper.selectByTransactionId(notifyRespData.getOut_trade_no());
        if (paymentRecord == null) {
            throw new BusinessException(ResultCode.ORDER_NOT_EXIST_ERROR);
        }
        checkSign(msg, notifyRespData, paymentRecord);
        if (paymentRecord.getTotalAmount().intValue() != Integer.parseInt(notifyRespData.getCash_fee())) {
            throw new SystemException(ErrorCode.TENPAY_PAYAMOUNT_ERROR, "微信通知金额校验失败");
        }
        paymentRecord.setOutTransactionId(notifyRespData.getTransaction_id());
        paymentRecord.setThirdUserId(notifyRespData.getOpenid());
        return paymentRecord;
    }


    private void checkSign(String msg, NotifyRespData notifyRespData, PaymentRecord paymentRecord) {

        boolean signValid;
        try {
            signValid = WeixinSignature.checkIsSignValidFromResponseString(msg, notifyRespData.getTrade_type(),
                    Integer.valueOf(paymentRecord.getMerchantId().toString()));
        } catch (Exception ex) {
            throw new SystemException(ErrorCode.TENPAY_GENERATE_SIGN_ERROR, ErrorCode.TENPAY_GENERATE_SIGN_ERROR.getDesc());
        }
        if (!signValid) {
            logger.error("微信返回的数据签名验证失败，有可能数据被篡改了");
            throw new SystemException(ErrorCode.TENPAY_SIGN_ERROR, ErrorCode.TENPAY_SIGN_ERROR.getDesc());
        }
    }

    @Override
    public NotifyRespData msgCheck(String message) throws Exception {
        logger.info("weiChat_message_check_for_message：" + message);
        NotifyRespData notifyRespData = (NotifyRespData) Util.getObjectFromXML(message, NotifyRespData.class);
        if (notifyRespData == null || notifyRespData.getReturn_code() == null) {
            logger.error("微信订单查询API请求逻辑错误，请仔细检测传过去的每一个参数是否合法，或是看API能否被正常访问");
            throw new SystemException(ErrorCode.TENPAY_SYS_ERROR, ErrorCode.TENPAY_SYS_ERROR.getDesc());
        }
        if (notifyRespData.getReturn_code().equals("FAIL")) {
            logger.error("微信订单查询API系统返回失败，请检测Post给API的数据是否规范合法");
            throw new SystemException(ErrorCode.TENPAY_NOTIFY_ERROR, notifyRespData.getReturn_msg());
        } else {
            logger.info("微信订单查询API系统成功返回数据");
            if (notifyRespData.getResult_code().equals("FAIL")) {
                logger.error("出错，错误码：" + notifyRespData.getErr_code() + "     错误信息：" + notifyRespData.getErr_code_des());
                logger.error("微信订单查询失败");
                throw new SystemException(ErrorCode.TENPAY_SYS_ERROR, notifyRespData.getErr_code_des());
            }
        }
        return notifyRespData;
    }
}
