package me.smart.order.redis.impl;

import me.smart.order.enums.ErrorCode;
import me.smart.order.exception.SystemException;
import me.smart.order.redis.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * Created by zhangxiong on 16/3/13.
 */
public class RedisServiceSpringDataImpl<V> implements RedisService {

    private static Logger logger = LoggerFactory.getLogger(RedisServiceSpringDataImpl.class);

    @Resource
    private RedisTemplate<String, V> redisTemplate;

    @Override
    public boolean put(String key, String value, Long timeout) {
        return put(key, value, timeout, false);
    }

    @Override
    public boolean put(String key, String value, Long timeout, boolean ifAbsent) {
        RedisSerializer<String> serializer = redisTemplate.getStringSerializer();

        byte[] bytesKey = serializer.serialize(key);
        byte[] bytesValue = serializer.serialize(value);

        return redisTemplate.execute((RedisConnection connection) -> {
            boolean success = true;
            if (ifAbsent) {
                success = connection.setNX(bytesKey, bytesValue);
            } else {
                connection.set(bytesKey, bytesValue);
            }

            if (timeout != null && timeout > 0) {
                connection.expire(bytesKey, timeout);
            }

            return success;
        });
    }

    @Override
    public String get(String key) {
        RedisSerializer<String> serializer = redisTemplate.getStringSerializer();

        byte[] bytesKey = serializer.serialize(key);
        byte[] bytesValue = redisTemplate.execute((RedisConnection connection) -> connection.get(bytesKey));
        if (bytesValue == null)
            return null;

        try {
            return new String(bytesValue, "utf-8");
        } catch (UnsupportedEncodingException e) {
            logger.error("Failed to convert bytes from redis to String", e);
            throw new SystemException(ErrorCode.REDIS_ERROR);
        }
    }

    @Override
    public boolean delete(String key) {
        RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
        byte[] bytesKey = serializer.serialize(key);

        return redisTemplate.execute((RedisConnection connection) -> (connection.del(bytesKey) > 0));
    }

    @Override
    public boolean putObject(String id, Object value, Long timeout) {
        // TODO:
        throw new UnsupportedOperationException("Method not implemented yet...");
    }

    @Override
    public Object getObject(String id) {
        // TODO:
        throw new UnsupportedOperationException("Method not implemented yet...");
    }

    @Override
    public String getFromList(String key, int index) {
        RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
        byte[] bytesKey = serializer.serialize(key);

        return serializer.deserialize(redisTemplate.execute((RedisConnection connection) -> connection.lIndex(bytesKey,
                index)));
    }

    @Override
    public int pushToList(String key, Object value) {
        RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
        byte[] bytesKey = serializer.serialize(key);
        byte[] bytesValue = serializer.serialize(value.toString());

        return redisTemplate.execute((RedisConnection connection) -> connection.rPush(bytesKey, bytesValue)).intValue();
    }

    @Override
    public int removeFromList(String key, Object value) {
        RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
        byte[] bytesKey = serializer.serialize(key);
        byte[] bytesValue = serializer.serialize(value.toString());

        return redisTemplate.execute((RedisConnection connection) -> connection.lRem(bytesKey, 1L, bytesValue))
                .intValue();
    }

    @Override
    public int addToSet(String key, Object[] values) {
        RedisSerializer<String> serializer = redisTemplate.getStringSerializer();

        byte[] bytesKey = serializer.serialize(key);
        byte[][] bytesValues = new byte[values.length][];
        for (int i = 0; i < values.length; i++) {
            bytesValues[i] = serializer.serialize(values[i].toString());
        }

        return redisTemplate.execute((RedisConnection connection) -> connection.sAdd(bytesKey, bytesValues)).intValue();
    }

    @Override
    public String popFromSet(String key) {
        RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
        byte[] bytesKey = serializer.serialize(key);

        return serializer.deserialize(redisTemplate.execute((RedisConnection connection) -> connection.sPop(bytesKey)));
    }

    @Override
    public boolean expire(String key, long seconds) {
        RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
        byte[] bytesKey = serializer.serialize(key);

        return redisTemplate.execute((RedisConnection connection) -> connection.expire(bytesKey, seconds));
    }

    @Override
    public boolean expireAt(String key, long unixTime) {
        RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
        byte[] bytesKey = serializer.serialize(key);

        return redisTemplate.execute((RedisConnection connection) -> connection.expireAt(bytesKey, unixTime));
    }

    @Override
    public Boolean hset(String key, String field, String value) {
        RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
        return redisTemplate.execute((RedisConnection connection) -> connection.hSet(serializer.serialize(key),
                serializer.serialize(field), serializer.serialize(value)));
    }

    @Override
    public Long hdel(String key, String field) {
        RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
        return redisTemplate.execute((RedisConnection connection) -> connection.hDel(serializer.serialize(key),
                serializer.serialize(field)));
    }

    @Override
    public List<String> hgetall(String key) {
        RedisSerializer<String> serializer = redisTemplate.getStringSerializer();

        List<byte[]> resultTmp = redisTemplate.execute((RedisConnection connection) -> connection.hVals(serializer
                .serialize(key)));

        List<String> result = new ArrayList<>();
        resultTmp.forEach(item -> {
            result.add(serializer.deserialize(item));
        });

        return result;
    }

    @Override
    public Map<String, String> hgetMap(String key) {
        RedisSerializer<String> serializer = redisTemplate.getStringSerializer();

        Map<String, String> result = new HashMap<>();

        Map<byte[], byte[]> map = redisTemplate.execute((RedisConnection connection) -> connection.hGetAll(serializer
                .serialize(key)));
        map.forEach((k, v) -> result.put(serializer.deserialize(k), serializer.deserialize(v)));

        return result;
    }

    /**
     * 有序zset集合,添加新的元素
     */
    @Override
    public Boolean zsetAdd(String key, long score, String value) {
        RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
        return redisTemplate.execute((RedisConnection connection) -> connection.zAdd(serializer.serialize(key), score,
                serializer.serialize(value)));
    }

    /**
     * 根据Score范围获取元素
     */
    @Override
    public List<String> getZsetRangeByScore(String key, long min, long max) {
        RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
        Set<byte[]> rangeSet = redisTemplate.execute((RedisConnection connection) -> connection.zRangeByScore(
                serializer.serialize(key), min, max));
        List<String> result = new ArrayList<String>();
        rangeSet.forEach(item -> {
            result.add(serializer.deserialize(item));
        });
        return result;
    }

    /**
     * 去除元素
     */
    @Override
    public Long removeZsetElement(String key, String value) {
        RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
        Long number = redisTemplate.execute((RedisConnection connection) -> connection.zRem(serializer.serialize(key),
                serializer.serialize(value)));
        return number;
    }

    /**
     * 获取数量
     */
    @Override
    public Long getZsetCount(String key, long min, long max) {
        RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
        Long count = redisTemplate.execute((RedisConnection connection) -> connection.zCount(serializer.serialize(key),
                min, max));
        return count;
    }

    @Override
    public Long getKeyValidSeconds(String key) {
        RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
        Long validSeconds = redisTemplate.execute((RedisConnection connection) -> connection.ttl(serializer
                .serialize(key)));
        return validSeconds;
    }
}
