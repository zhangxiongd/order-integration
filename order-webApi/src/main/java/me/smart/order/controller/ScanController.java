package me.smart.order.controller;

import me.smart.order.constant.TenPayConstant;
import me.smart.order.model.Member;
import me.smart.order.service.MemberService;
import me.smart.order.service.MenuService;
import me.smart.order.weixin.WeixinSignService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.text.MessageFormat;
import java.util.Map;

/**
 * Created by zhangxiong on 16/3/12.
 * 用于扫描二维码的请求处理的controller
 */
@Controller
@RequestMapping("/scan")
public class ScanController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(ScanController.class);
    private static final String MENU_URL_PATTERN = "{0}?merchantId={1}&tableNo={2}&memberId={3}";
    @Resource
    private MenuService menuService;
    @Resource
    private WeixinSignService weixinSignService;
    @Resource
    private MemberService memberService;

    @Value("redirect_url")
    private String redirectUrl;
    @Value("menu_url")
    private String menuUrl;
    @Value("error_url")
    private String errorUrl;


    /**
     * 扫描二维码 此二维码仅支持微信扫码
     *
     * @param merchantId
     * @param tableNo
     * @param sign
     * @param request
     * @return
     */
    @RequestMapping(value = "/scanCode/{merchantId}/{tableNo}/{sign}/{from}")
    public String authFromQRCode(@PathVariable Long merchantId, @PathVariable String tableNo,
                                 @PathVariable String sign, @PathVariable String from) {
        logger.info("二维码扫描参数,merchantId={},tableNo={},sign={} ", merchantId, tableNo, sign);
        //todo  验签
        try {
            String authUrl = TenPayConstant.getAuthUrl(redirectUrl, merchantId + "_" + tableNo + "_" + sign);
            logger.info("微信网页授权url={}", authUrl);
            return "redirect:" + authUrl;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            //todo 当扫描二维码时 抛出异常时的处理
            return "redirect:" + errorUrl;
        }
    }


    /**
     * 微信授权回调url
     *
     * @param code  授权码
     * @param state redirect_uri携带业务参数，微信直接返回
     */
    @RequestMapping(value = "/authCallBack", method = RequestMethod.GET)
    public String authCallBack(String code, String state, HttpServletResponse response) {
        //根据auth_code获取openId
        logger.info("授权回调参数,code={},state={}", code, state);
        /**
         * 根据授权获取用户的openId
         */
        try {
            Map<String, Object> openMap = weixinSignService.getOpenId(code);
            Map<String, Object> userInfoMap = weixinSignService.getUserInfo(openMap.get("access_token").toString(), openMap.get("openid").toString());
            /**
             * 获取微信的信息后，需要创建会员
             */
            String[] states = state.split("_");
            Member member = memberService.createMember(userInfoMap);
            String redirectUrl = MessageFormat.format(MENU_URL_PATTERN, menuUrl, states[0], states[1], member.getId());
            logger.info("redirect url={}", redirectUrl);
            //转发给浏览器，然后根据浏览器再根据url截取参数，再post后端获取餐厅列表
            return "redirect:" + redirectUrl;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return "redirect:" + errorUrl;
        }
    }


}
