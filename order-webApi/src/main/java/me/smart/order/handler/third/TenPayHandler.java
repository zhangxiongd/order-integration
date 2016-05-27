package me.smart.order.handler.third;

import me.smart.order.api.PaymentInfo;
import me.smart.order.business.tenpay.TenPayResultBusiness;
import me.smart.order.business.tenpay.TenPayUnifiedOrderBusiness;
import me.smart.order.constant.TenPayConstant;
import me.smart.order.dto.payment.RefundRequest;
import me.smart.order.dto.payment.RefundRespResult;
import me.smart.order.dto.tenpay.UnifiedOrderReqData;
import me.smart.order.dto.tenpay.UnifiedOrderRespData;
import me.smart.order.enums.PaymentWay;
import me.smart.order.enums.ResultCode;
import me.smart.order.enums.TradeType;
import me.smart.order.exception.BusinessException;
import me.smart.order.exception.SystemException;
import me.smart.order.model.PaymentOrder;
import me.smart.order.model.PaymentRecord;
import me.smart.order.model.PaymentRefund;
import me.smart.order.service.PaymentService;
import me.smart.order.util.ConvertUtil;
import me.smart.order.util.NetUtil;
import me.smart.order.util.RandomStringGenerator;
import me.smart.order.util.TenPaySignature;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by zhangxiong on 16/3/20.
 */
public class TenPayHandler extends BaseThirdPayHandler {

    Logger logger = LoggerFactory.getLogger(TenPayHandler.class);


    @Value("tenpay_notify_url")
    private String notifyUrl;
    @Resource
    private PaymentService paymentService;
    @Resource
    private TenPayUnifiedOrderBusiness tenPayUnifiedOrderBusiness;

    @Override
    protected PaymentRecord pushPayment(PaymentOrder order, PaymentInfo paymentInfo, Map<String, Object> ext) throws BusinessException {
        PaymentWay paymentWay = paymentInfo.getPaymentWay();
        TradeType tradeType = paymentInfo.getTradeType();
        if (ext.get("openId") == null) {
            throw new BusinessException(ResultCode.TENPAY_SYS_ERROR, ResultCode.TENPAY_SYS_ERROR.getDesc());
        }
        String openId = ext.get("openId").toString();
        PaymentRecord paymentRecord = paymentService.selectByMidAndNoAndPayType(order, paymentWay, tradeType);

        Map<String, String> payData = createPayData(ext);
//        Map<String, String> payUrls = createPayUrls(ext);

        if (paymentRecord == null) {
            //如果此支付方式没有对应的流水，则新创建一个流水并且向微信发起统一下单
            paymentRecord = paymentService.insertPaymentRecord(order, paymentInfo);

            logger.info("向微信发起统一下单支付，transaction = {}", paymentRecord.getTransactionId());

            try {
                if (PaymentWay.TEN_PAY.getPaymentWay().equals(paymentRecord.getPaymentWay())) {
                    UnifiedOrderReqData unifiedOrderReqData = getUnifiedOrderReqData(order, paymentRecord, openId);
                    logger.info("向微信发起统一下单请求");
                    UnifiedOrderRespData unifiedOrderRespData = tenPayUnifiedOrderBusiness.process(unifiedOrderReqData);

                    //将微信返回的prepayId设置到paymentRecord返回出去，调用者会将其更新到数据库
                    paymentRecord.setPrepayId(unifiedOrderRespData.getPrepay_id());
                }
            } catch (SystemException e) {
                logger.error("微信统一下单报错 errorMessage={}", e.getMessage(), e);
                throw e;
            } catch (Exception e) {
                logger.error("微信统一下单失败 errorMessage={}", e.getMessage(), e);
            }
        }
        String result = TenPayResultBusiness.getDetail(paymentRecord.getPrepayId(), tradeType.name());
        //不同的交易方式返回不同的参数
        if (TradeType.JSAPI.name().equals(tradeType.name())) {
            payData.put(tradeType.name(), result);
        }
        return paymentRecord;
    }

    @Override
    protected RefundRespResult refund(RefundRequest refundRequest, PaymentRefund paymentRefund) {
        try{
            String certPath = "";
            String password = "";

        }catch (Exception e){

        }
        return null;
    }


    /**
     * 组装微信统一下单参数对象
     *
     * @param paymentOrder
     * @param paymentRecord
     * @param openId
     * @return
     */
    private UnifiedOrderReqData getUnifiedOrderReqData(PaymentOrder paymentOrder,
                                                       PaymentRecord paymentRecord, String openId) throws Exception {
        UnifiedOrderReqData requestData = new UnifiedOrderReqData();
        requestData.setAppid(TenPayConstant.APP_ID);
        requestData.setMch_id(TenPayConstant.MCHID);
        requestData.setDevice_info("");
        requestData.setNonce_str(RandomStringGenerator.getRandomStringByLength(32));
        //要支付的商品的描述信息，用户会在支付成功页面里看到这个信息
        requestData.setBody(paymentOrder.getOrderName());
        //商品详情
        requestData.setDetail(paymentOrder.getProductBody());
        /**
         * 支付订单里面可以填的附加数据，API会将提交的这个附加数据原样返回，
         *有助于商户自己可以注明该笔消费的具体内容，方便后续的运营和记录
         */
        requestData.setAttach(paymentOrder.getOrderData());
        requestData.setOut_trade_no(paymentOrder.getOutTradeNo());
        //订单总金额，单位为“分”，只能整数
        requestData.setTotal_fee(paymentRecord.getTotalAmount().intValue());
        requestData.setSpbill_create_ip(NetUtil.getIp());
        requestData.setTime_start(new DateTime(paymentOrder.getCreatedAt()).toString("yyyyMMddHHmmss"));
        requestData.setTime_expire(DateTime.now().plusSeconds(paymentOrder.getPayDeadLine()).toString("yyyyMMddHHmmss"));
        requestData.setGoods_tag("");
        requestData.setNotify_url(notifyUrl);
        requestData.setTrade_type(paymentRecord.getTradeType());
        requestData.setOpenid(openId);
        requestData.setSign(TenPaySignature.getSign(ConvertUtil.objectToMap(requestData)));
        return requestData;

    }


}
