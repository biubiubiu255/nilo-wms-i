package com.nilo.wms.service;

import com.nilo.wms.service.platform.RedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import redis.clients.jedis.Jedis;

import java.util.UUID;
import java.util.Vector;

/**
 * Created by admin on 2018/3/27.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/*.xml"})
public class RedisUtilTest {

    @Test
    public void testLock() {

        Jedis jedis1 = RedisUtil.getResource();
        String key = "key_lock";
        System.out.println("Jedis 1");
        System.out.println(jedis1.get(key));
        String requestId = UUID.randomUUID().toString();
        RedisUtil.tryGetDistributedLock(jedis1, key, requestId);

        Jedis jedis2 = RedisUtil.getResource();
        System.out.println("Jedis 2");
        System.out.println(jedis2.get(key));
        String requestId2 = UUID.randomUUID().toString();
        RedisUtil.tryGetDistributedLock(jedis2, key, requestId2);
        RedisUtil.releaseDistributedLock(jedis1, key, requestId);
        RedisUtil.releaseDistributedLock(jedis2, key, requestId2);
    }

    @Test
    public void testMultiLock() {

        String key_test = "test";
        // 使用线程安全的Vector
        Vector<Thread> threads = new Vector<Thread>();
        RedisUtil.set(key_test, "1");
        System.out.println(System.currentTimeMillis());
        for (int i = 0; i < 200; i++) {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    Jedis j = RedisUtil.getResource();
                    String key = "lock_lock";
                    String uuid = UUID.randomUUID().toString();
                    RedisUtil.tryGetDistributedLock(j, key, uuid);
                    Integer value = Integer.parseInt(j.get(key_test));
                    j.set(key_test, "" + (value + 1));
                    RedisUtil.releaseDistributedLock(j, key, uuid);

                }
            });
            threads.add(t);
            t.start();
        }
        for (Thread iThread : threads) {
            try {
                // 等待所有线程执行完毕
                iThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(RedisUtil.get(key_test));
        System.out.println(System.currentTimeMillis());

    }
}
