package me.smart.order.controller;

import me.smart.order.dao.MerchantMapper;
import me.smart.order.model.Merchant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhangxiong on 15/12/19.
 */
@Controller
@RequestMapping("/index")
public class IndexController {


    private Logger log = LoggerFactory.getLogger(IndexController.class);


    @Resource
    private MerchantMapper merchantMapper;

    @RequestMapping(value = "/list")
    @ResponseBody
    public Merchant index() {
        Map<String, Object> result = new HashMap<String, Object>();
        Merchant merchant = merchantMapper.getMerchantById(1l);
        return merchant;
    }
}
