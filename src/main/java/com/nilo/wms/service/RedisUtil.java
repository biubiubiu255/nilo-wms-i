package com.nilo.wms.service;

import com.nilo.wms.common.exception.SysErrorCode;
import com.nilo.wms.common.exception.WMSException;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * Created by admin on 2017/10/9.
 */
public class RedisUtil {

    /**
     * redis中sku库存key
     */
    public static final String STORAGE = "storage";
    /**
     * redis中sku锁定库存key
     */
    public static final String LOCK_STORAGE = "lockStorage";

    public static final String LOCK_TIME = "lockTime";

    private static final String LOCK_SUCCESS = "OK";
    private static final String SET_IF_NOT_EXIST = "NX";
    private static final String SET_WITH_EXPIRE_TIME = "PX";
    private static final Long RELEASE_SUCCESS = 1L;
    public static final String LOCK_KEY = "wms_redis_lock_key";

    private static JedisPool jedisPool = SpringContext.getBean("jedisPool", JedisPool.class);

    public static Jedis getResource() {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis;
        } catch (Exception e) {
            if (null != jedis)
                jedis.close();
        }
        return null;
    }

    public static void returnResource(Jedis jedis) {
        jedis.close();
    }

    /**
     * 尝试获取分布式锁
     *
     * @param jedis     Redis客户端
     * @param lockKey   锁
     * @param requestId 请求标识
     * @return 是否获取成功
     */
    public static boolean tryGetDistributedLock(Jedis jedis, String lockKey, String requestId) {

        for (int i = 0; i < 10; i++) {
            String result = jedis.set(lockKey, requestId, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, 5000);
            if (LOCK_SUCCESS.equals(result)) {
                return true;
            } else {
                try {
                    Thread.sleep(100 + (int) Math.random() * 10);
                } catch (Exception e) {

                }
            }
        }
        returnResource(jedis);
        return false;

    }

    /**
     * 释放分布式锁
     *
     * @param jedis     Redis客户端
     * @param lockKey   锁
     * @param requestId 请求标识
     * @return 是否释放成功
     */
    public static boolean releaseDistributedLock(Jedis jedis, String lockKey, String requestId) {

        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        Object result = jedis.eval(script, Collections.singletonList(lockKey), Collections.singletonList(requestId));

        if (RELEASE_SUCCESS.equals(result)) {
            return true;
        }
        return false;

    }

    public static void del(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.del(key);
        } finally {
            if (null != jedis) {
                jedis.close();
            }
        }
    }

    public static void set(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.set(key, value);
        } finally {
            if (null != jedis) {
                jedis.close();
            }
        }
    }

    public static String get(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String value = jedis.get(key);
            return value;
        } finally {
            if (null != jedis) {
                jedis.close();
            }
        }
    }

    public static boolean hasKey(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.exists(key);
        } finally {
            if (null != jedis) {
                jedis.close();
            }
        }
    }

    public static void hset(String key, String field, String value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.hset(key, field, value);
        } finally {
            if (null != jedis) {
                jedis.close();
            }
        }
    }

    public static String hget(String key, String field) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.hget(key, field);
        } finally {
            if (null != jedis) {
                jedis.close();
            }
        }
    }

    public static Set<String> hkeys(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.hkeys(key);
        } finally {
            if (null != jedis) {
                jedis.close();
            }
        }
    }

    public static boolean hhasKey(String key, String field) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.hexists(key, field);
        } finally {
            if (null != jedis) {
                jedis.close();
            }
        }
    }

    public static Set<String> keys(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.keys(key);
        } finally {
            if (null != jedis) {
                jedis.close();
            }
        }
    }

    public static String getSkuKey(String clientCode, String sku) {
        return "wms_" + clientCode + "_sku_" + sku;
    }

    public static String getLockOrderKey(String clientCode, String orderNo) {
        return "wms_" + clientCode + "_lock_order_" + orderNo;
    }
}
