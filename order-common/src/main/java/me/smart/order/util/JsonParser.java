package me.smart.order.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import me.smart.order.enums.ErrorCode;
import me.smart.order.exception.SystemException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * @deprecated Use JsonConvertUtils instead
 */
public class JsonParser {

    Logger logger = LoggerFactory.getLogger(this.getClass());


    /**
     * 根据指定类型把jsonStr转换为Java对象
     *
     * @param jsonStr
     * @param clazz
     * @return
     * @throws JsonProcessingException
     * @throws IOException
     */
    public static <T> T convertToClazz(String jsonStr, Class<T> clazz)
            throws IOException {
        JsonNode jsonNode = null;
        ObjectMapper mapper = new ObjectMapper();
        jsonNode = mapper.readTree(jsonStr);
        T obj = mapper.convertValue(jsonNode, clazz);
        return obj;
    }

    /**
     * 根据指定类型把包问题内容转换为Java对象
     *
     * @param clazz 指定转换类型
     * @return 转换后的Java对象
     */
    public static HashMap<String, Object> convertToMap(String msg)
            throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(msg);
        HashMap<String, Object> objMap = mapper.convertValue(jsonNode,
                HashMap.class);
        return objMap;
    }

    public static HashMap<String, String> convertToMapString(String msg)
            throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(msg);
        HashMap<String, String> objMap = mapper.convertValue(jsonNode,
                HashMap.class);
        return objMap;
    }


    /**
     * 将Map转化为String
     *
     * @param parmMap
     * @param isEncode 是否url编码
     * @return
     */
    public static String converMapToJson(Map<String, Object> parmMap,
                                         boolean isEncode) throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode data = objectMapper.createObjectNode();
        for (Map.Entry<String, Object> entry : parmMap.entrySet()) {
            if (isEncode && !entry.getKey().equals("sign")) {
                if (entry.getValue() != null)
                    data.putPOJO(entry.getKey(), URLEncoder.encode(
                            String.valueOf(entry.getValue()), "UTF-8"));
            } else {
                if (entry.getValue() != null)
                    data.putPOJO(entry.getKey(), String.valueOf(entry.getValue()));
            }
        }
        return objectMapper.writeValueAsString(data);
    }

    /**
     * 将对象转化为map
     *
     * @param obj
     * @return
     */
    public static Map<String, Object> objectToMap(Object object)
            throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        Field[] fields = object.getClass().getDeclaredFields();
        try {
            for (Field field : fields) {
                Object obj;
                field.setAccessible(true);
                obj = field.get(object);
                if (obj != null && !field.getName().equals("serialVersionUID")) {
                    map.put(field.getName(), obj);
                }
            }
        } catch (Exception e) {
            throw new SystemException(ErrorCode.PARSE_ERROR);
        } finally {
            if (map == null) {
                throw new SystemException(ErrorCode.PARSE_ERROR);
            }
        }
        return map;
    }

    /**
     * 将对象转化成json String
     *
     * @return
     * @throws Exception
     */
    public static String objectToJsonStr(Object object, boolean isEncode)
            throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode data = objectMapper.createObjectNode();
        Field[] fields = object.getClass().getDeclaredFields();
        Object obj;
        for (Field field : fields) {
            field.setAccessible(true);
            obj = field.get(object);
            if (obj != null && !field.getName().equals("serialVersionUID")) {
                if (isEncode && !field.getName().equals("sign")) {
                    obj = URLEncoder.encode(obj.toString(), "UTF-8");
                }
                data.putPOJO(field.getName(), obj);
            }
        }
        return objectMapper.writeValueAsString(data);
    }
}
