package com.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sun.nio.cs.ext.GBK;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author haoxl
 * @Title:
 * @Package
 * @Description:
 * @date 2021/2/4 10:46
 */
@Slf4j
@RequestMapping("/redis")
@RestController
public class RedisDemo {


    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping(value = "/getKey", method = RequestMethod.GET)
    public String getRedisString(@RequestParam @Valid @NotNull(message = "未知的key") String key) {

        Object value = redisTemplate.opsForValue().get(key);
        if (Objects.nonNull(value) && value instanceof String) {
            return String.valueOf(value);
        }
        return "未找到对应的值";
    }

    @RequestMapping(value = "/getKeySpecial", method = RequestMethod.GET)
    public String getRedisStringBy(@RequestParam String key) {

        Object value = redisTemplate.getConnectionFactory().getConnection().get(key.getBytes(GBK.defaultCharset()));

        if (Objects.nonNull(value) && value instanceof String) {
            return String.valueOf(value);
        } else if (value instanceof byte[]) {
            return new String((byte[]) value);
        }
        return "未找到对应的值";
    }


    @PostMapping("/addRedisValue")
    public String setRedisString(@RequestBody @Valid @NotNull(message = "目标对象为空") Map<String, String> map) {
        AtomicInteger atomicInteger = new AtomicInteger(1);
        map.entrySet().stream().filter(t -> Objects.nonNull(t.getKey())).forEach(t -> {
            redisTemplate.opsForValue().set(t.getKey(), t.getValue(), 1000, TimeUnit.SECONDS);
            atomicInteger.incrementAndGet();
        });

        try {
            LettuceConnectionFactory lettuceConnectionFactory = (LettuceConnectionFactory) redisTemplate.getConnectionFactory();
            lettuceConnectionFactory.setDatabase(3);
            lettuceConnectionFactory.getConnection().set("key1".getBytes(), "value1".getBytes());
            System.out.println("指定database----写入key:value");
        } catch (Exception e) {
            log.error("指定database----写入redis异常:{}", e.getMessage());
        }
        return String.format("操作成功，设置目标key-value个数为%s个", atomicInteger.toString());
    }
}
