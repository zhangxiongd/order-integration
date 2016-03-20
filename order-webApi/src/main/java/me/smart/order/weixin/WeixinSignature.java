package me.smart.order.weixin;

import me.smart.order.constant.TenPayConstant;
import me.smart.order.util.MD5;
import me.smart.order.util.SHAUtil;
import me.smart.order.util.XMLParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public class WeixinSignature {

    private static Logger log = LoggerFactory.getLogger(WeixinSignature.class);


    public static String getSign(Map<String, Object> map, String tradeType, Integer merchantId) {
        ArrayList<String> list = new ArrayList<String>();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            if (!org.springframework.util.StringUtils.isEmpty(entry.getValue())) {
                list.add(entry.getKey() + "=" + entry.getValue() + "&");
            }
        }
        int size = list.size();
        String[] arrayToSort = list.toArray(new String[size]);
        Arrays.sort(arrayToSort, String.CASE_INSENSITIVE_ORDER);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            sb.append(arrayToSort[i]);
        }
        String result = sb.toString();

        result += "key=" + TenPayConstant.KEY;
        log.info("Sign Before MD5:" + result);
        result = MD5.MD5Encode(result).toUpperCase();
        log.info("Sign Result MD5:" + result);
        return result;
    }

    public static String getSignSha1(Map<String, Object> map) {
        ArrayList<String> list = new ArrayList<String>();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            if (entry.getValue() != "") {
                list.add(entry.getKey() + "=" + entry.getValue());
            }
        }
        int size = list.size();
        String[] arrayToSort = list.toArray(new String[size]);
        Arrays.sort(arrayToSort, String.CASE_INSENSITIVE_ORDER);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size - 1; i++) {
            sb.append(arrayToSort[i]).append("&");
        }
        sb.append(arrayToSort[size - 1]);
        String result = sb.toString();
        log.info("Sign Before SHA1:" + result);
        result = SHAUtil.SHA1(result);
        log.info("Sign Before SHA1:" + result);
        return result;
    }

    /**
     * 检验API返回的数据里面的签名是否合法，避免数据在传输的过程中被第三方篡改
     *
     * @param responseString API返回的XML数据字符串
     * @return API签名是否合法
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException
     */
    public static boolean checkIsSignValidFromResponseString(String responseString, String tradeType, Integer merchantId)
            throws ParserConfigurationException, IOException, SAXException {

        Map<String, Object> map = XMLParser.xmlToMap(responseString);
        log.info(map.toString());

        String signFromAPIResponse = map.get("sign").toString();
        if (signFromAPIResponse == "" || signFromAPIResponse == null) {
            log.error("API返回的数据签名数据不存在，有可能被第三方篡改!!!");
            return false;
        }
        log.info("服务器回包里面的签名是:" + signFromAPIResponse);
        map.put("sign", "");
        String signForAPIResponse = WeixinSignature.getSign(map, tradeType, merchantId);

        if (!signForAPIResponse.equals(signFromAPIResponse)) {
            log.error("API返回的数据签名验证不通过，有可能被第三方篡改!!!");
            return false;
        }
        log.info("恭喜，API返回的数据签名验证通过!!!");
        return true;
    }


}
