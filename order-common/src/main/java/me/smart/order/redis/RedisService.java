package me.smart.order.redis;

import java.util.List;
import java.util.Map;

/**
 * Created by zhangxiong on 16/3/13.
 */
public interface RedisService {
    String KEY_SPLIT = ":";

    default String getKey(Object... paramKeys) {
        StringBuilder builder = new StringBuilder();
        for (Object paramKey : paramKeys) {
            builder.append(paramKey).append(KEY_SPLIT);
        }

        return builder.deleteCharAt(builder.length() - 1).toString();
    }

    default int getInt(String key, int defaultValue) {
        String str = get(key);
        return (str != null) ? Integer.parseInt(str) : defaultValue;
    }

    /**
     * 向Redis放置一个键值
     *
     * @param key     键
     * @param value   值
     * @param timeout 过期时间（秒），传null或负数时表示不过期
     * @return 是否成功
     */
    boolean put(String key, String value, Long timeout);

    /**
     * 向Redis放置一个键值
     *
     * @param key      键
     * @param value    值
     * @param timeout  过期时间（秒），传null或负数时表示不过期
     * @param ifAbsent true时仅当键不存在时放置，键存在时返回失败
     * @return 是否成功
     */
    boolean put(String key, String value, Long timeout, boolean ifAbsent);

    String get(String key);

    boolean delete(String key);

    boolean putObject(String id, Object value, Long timeout);

    Object getObject(String id);

    String getFromList(String key, int index);

    int pushToList(String key, Object value);

    int removeFromList(String key, Object value);

    int addToSet(String key, Object[] values);

    String popFromSet(String key);

    boolean expire(String key, long seconds);

    boolean expireAt(String key, long unixTime);

    Boolean hset(String key, String field, String value);

    Long hdel(String key, String field);

    List<String> hgetall(String key);

    Map<String, String> hgetMap(String key);

    Boolean zsetAdd(String key, long score, String value);

    List<String> getZsetRangeByScore(String key, long min, long max);

    Long getZsetCount(String key, long min, long max);

    Long removeZsetElement(String key, String value);

    Long getKeyValidSeconds(String key);

    //	boolean putNx(String key,String value,Long timeout);
}
