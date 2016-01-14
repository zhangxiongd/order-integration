package me.smart.order.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhangxiong on 15/12/19.
 */
@Controller
@RequestMapping("/index")
public class IndexController {


    private Logger log = LoggerFactory.getLogger(IndexController.class);

    @RequestMapping(value = "/list")
    @ResponseBody
    public Map<String, Object> index() {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("name", "zhangxiong");
        log.info("result={}", result);
        return result;
    }
}
