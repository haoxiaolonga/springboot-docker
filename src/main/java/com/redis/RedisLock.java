package com.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author haoxl
 * @Title:
 * @Package
 * @Description:
 * @date 2021/2/7 9:22
 */
@Slf4j
@Component
public class RedisLock {

    @Autowired
    private RedisTemplate redisTemplate;

    private static final String LOCK_SUCCESS = "OK";

    /**
     * @param key    锁标志
     * @param value  值，可用请求线程ID。唯一最好
     * @param expire 过期时间
     * @return
     */
    public boolean tryGetDistributedLock(String key, String value, long expire) {

        if (StringUtils.isEmpty(key)) {
            log.info("没有设置Lock的key");
            return false;
        }
        if (expire <= 0) {
            expire = 30L;
        }
        return redisTemplate.opsForValue().setIfAbsent(key, value, expire, TimeUnit.SECONDS);
    }


    /**
     * 释放redis 锁
     *
     * @param key
     * @param value
     * @return
     */
    public boolean releaseDistributedLock(String key, String value) {

        if (StringUtils.isEmpty(key)) {
            log.info("没有查询Lock的key");
            return false;
        }
        //
        String script1 = " return  redis.call('get', KEYS[1])  ";
        String script2 = " return (redis.call('get', KEYS[1]) == ARGV[1])  ";
        String script4 = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        String script3 = " return ARGV[1] ";

        DefaultRedisScript<String> getRedisScript = new DefaultRedisScript<>(script1, String.class);
        Object result = redisTemplate.execute(getRedisScript, Collections.singletonList(key), value);
        System.out.println("获取到的值1:" + result);

        DefaultRedisScript<Boolean> getRedisScript2 = new DefaultRedisScript<>(script2, Boolean.class);
        Object result2 = redisTemplate.execute(getRedisScript2, Collections.singletonList(key), new Object[]{value});
        System.out.println("获取到的值2:" + result2);

        DefaultRedisScript<Long> getRedisScript4 = new DefaultRedisScript<>(script4, Long.class);
        Object result4 = redisTemplate.execute(getRedisScript4, Collections.singletonList(key),  new Object[]{value});
        System.out.println("完整获取到的值4:" + result4);

        DefaultRedisScript<Long> getRedisScript3 = new DefaultRedisScript<>(script3);
        Object result3 = redisTemplate.execute(getRedisScript3, Collections.singletonList(key), Collections.singletonList(value));
        System.out.println("发过去的参数值为:" + result3);


        if ((long)result4 > 0) {
            return true;
        }
        return false;
    }
}
