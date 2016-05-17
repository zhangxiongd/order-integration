package me.smart.order.service.impl;

import me.smart.order.model.PaymentRecord;
import me.smart.order.notify.impl.AcceptTenpayNotify;
import me.smart.order.service.PaymentNotifyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by zhangxiong on 16/5/14.
 */
@Service("paymentNotifyService")
public class PaymentNotifyServiceImpl implements PaymentNotifyService {
    private Logger logger = LoggerFactory.getLogger(PaymentNotifyServiceImpl.class);

    @Resource
    private AcceptTenpayNotify acceptTenpayNotify;

    @Override
    public String acceptTenPayNotify(String msg) {
        logger.info("PaymentNotifyServiceImpl acceptTenPayNotify request={}", msg);

        try {
            PaymentRecord paymentRecord = acceptTenpayNotify.parse(msg);
            
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        return null;
    }
}
