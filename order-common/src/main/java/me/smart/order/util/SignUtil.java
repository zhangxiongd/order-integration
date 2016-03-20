package me.smart.order.util;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 通用的签名方法
 * Created by zhangxiong on 16/3/12.
 */
public class SignUtil {
    private static Logger logger = LoggerFactory.getLogger(SignUtil.class);
    private final static String sign_method_md5 = "MD5";

    /**
     * 签名算法
     * signMethod 为空时，默认为MD5加密
     *
     * @param map
     * @param signMethod
     * @param authKey
     * @return
     */
    public static String signMD5(Map<String, Object> map, String authKey) {
        if (map.size() == 0) {
            return "";
        }
        map.remove("sign");
        map.remove("signMethod");
        StringBuilder signBuilder = buildSign(map);
        if (StringUtils.isNotBlank(authKey)) {
            signBuilder.append("&key=").append(authKey);
        }
        logger.info("签名源串为：" + signBuilder.toString());
        byte[] digest = DigestUtils.getDigest(sign_method_md5).digest(signBuilder.toString().getBytes());
        return new String(Hex.encodeHex(digest));
    }


    /**
     * 根据map获取待加密字符串
     *
     * @param map
     * @return
     */
    public static StringBuilder buildSign(Map<String, Object> map) {
        if (!(map instanceof TreeMap)) {
            map = new TreeMap<String, Object>(map);
        }
        StringBuilder result = new StringBuilder();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (value != null && StringUtils.isNotBlank(value.toString())) {
                result.append(key).append("=").append(signStr(value)).append("&");
            }
        }
        return result.deleteCharAt(result.length() - 1);
    }

    private static String signStr(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof Map) {
            return buildSign((Map) obj).insert(0, "{").append("}").toString();
        }

        if (obj instanceof List) {
            return buildSign((List) obj).insert(0, "{").append("}").toString();
        }
        return obj.toString();
    }


    private static StringBuilder buildSign(List<Object> list) {
        StringBuilder builder = new StringBuilder();

        //list为空的情况
        if (list == null || list.isEmpty()) {
            return builder;
        }

        for (Object obj : list) {
            builder.append(signStr(obj)).append(",");
        }

        return builder.deleteCharAt(builder.length() - 1);
    }


    /**
     * 验签
     *
     * @param map
     * @param authKey
     * @return
     */
    public static boolean checkSign(Map<String, Object> map, String authKey) {
        String requestSign = String.valueOf(map.remove("sign"));
        String sign = signMD5(map, authKey);
        if (!StringUtils.equals(sign, requestSign)) {
            logger.warn(" Check sign failed! calculated sign={} not equals requestSign={}", sign, requestSign);
            return false;
        }
        return true;
    }

}
