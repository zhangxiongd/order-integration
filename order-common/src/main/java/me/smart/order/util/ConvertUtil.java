package me.smart.order.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by zhangxiong on 16/3/12.
 */
public class ConvertUtil {

    private static Logger logger = LoggerFactory.getLogger(ConvertUtil.class);

    /**
     * 对象转map
     *
     * @param object
     * @return
     */
    public static Map objectToMap(Object object) {
        if (object == null) {
            return null;
        }
        Map<String, Object> result = new TreeMap<String, Object>();
        Field[] fields = object.getClass().getDeclaredFields();
        try {
            String key = null;
            Object value = null;
            for (Field field : fields) {
                field.setAccessible(true);
                key = field.getName();
                value = field.get(object);
                if (value != null && !"serialVersionUID".equals(key)) {
                    result.put(key, value);
                }
            }
            return result;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }

    }
}
