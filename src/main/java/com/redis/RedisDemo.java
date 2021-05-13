package com.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sun.nio.cs.ext.GBK;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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



    @RequestMapping(value = "/getHashKey", method = RequestMethod.GET)
    public String getHashKey(@RequestParam @Valid @NotNull(message = "未知的key") String key) {

        redisTemplate.opsForHash().put(key,"name","张三");
        redisTemplate.opsForHash().put(key,"tel","10086");
        redisTemplate.opsForHash().put(key,"sex","男");
        redisTemplate.opsForHash().put(key,"age",Integer.valueOf(1));


        Map x = redisTemplate.opsForHash().entries(key);

        //判断是否存在某个hash-key
        redisTemplate.opsForHash().hasKey(key,"name");

        //增加指定key的值
        redisTemplate.opsForHash().increment(key,"age",1);

        //获取该hash缓存下的key值。
        Set set = redisTemplate.opsForHash().keys(key);

        System.out.println( redisTemplate.opsForHash().get(key,"age"));

        //获取缓存元素值的长度
        System.out.println(redisTemplate.opsForHash().lengthOfValue(key,"tel"));

        //批量获取指定key的value
        redisTemplate.opsForHash().multiGet(key,new ArrayList());

        //获取hasH缓存中的个数
        redisTemplate.opsForHash().size(key);

        Cursor xz = redisTemplate.opsForHash().scan(key,ScanOptions.scanOptions().count(100).match("*").build());

        return "测试 redis hash";
    }




    @RequestMapping(value = "/getKey", method = RequestMethod.GET)
    public String getRedisString(@RequestParam @Valid @NotNull(message = "未知的key") String key) {

        Object value = redisTemplate.opsForValue().get(key);
        if (Objects.nonNull(value) && value instanceof String) {
            return String.valueOf(value);
        }
        return "未找到对应的值";
    }

    @RequestMapping(value = "/setList", method = RequestMethod.GET)
    public String setList(@RequestParam @Valid @NotNull(message = "未知的key") String key) {

        redisTemplate.opsForList().trim(key,1,0);
        redisTemplate.opsForList().rightPushAll(key, Stream.of(4,1,2,4,666,7,2,3,1).sorted().collect(Collectors.toList()));

        //移除不在该区间内的元素。
        redisTemplate.opsForList().trim(key,1,0);

        //从右端推入一串
        redisTemplate.opsForList().rightPushAll(key,1L);
        //从右边插入一个
        redisTemplate.opsForList().rightPush(key,100);

        //再给定值v1的后面推入值.
        redisTemplate.opsForList().rightPush(key, 1,2);

        /**
         * 底层是lrem 移除列表与参数v2 相等的元素
         *
         * v1 > 0, 从列表尾开始匹配，v1个都删除 （尾部再右边）
         * v1 <0 从列表头开始匹配，删除v1的绝对值个（头部指）
         * v1=0 删除所有与v2 相等的元素
         */
        redisTemplate.opsForList().remove(key,1,1);

        /**
         * 返回返回类的列表元素。
         */
        redisTemplate.opsForList().range(key,100,100);

        /**
         * 对列表进行处理，保留区间内的元素，不在区间内全删除，如果start 大于0，end小于start.全部删除。
         */
        redisTemplate.opsForList().trim(key,1,-1);

        /**
         * 返回列表的大小
         */
        redisTemplate.opsForList().size(key);

        /**
         * 如果存在列表，则再尾部推入元素
         */
        redisTemplate.opsForList().rightPushIfPresent(key+"if",1);

        //从尾部弹出一个列表元素，如果没有等待指定的超时时间。
        redisTemplate.opsForList().rightPop(key+"if",222,TimeUnit.SECONDS);

        //从尾部弹出一个列表元素
        redisTemplate.opsForList().rightPop(key+"if");

        //通过索引获取列表该位置的元素
        redisTemplate.opsForList().index(key, 10);

        //从列表头部弹出元素
        redisTemplate.opsForList().leftPop(key);

        //从列表头部弹出元素
        redisTemplate.opsForList().leftPop(key,2,TimeUnit.SECONDS);

        // 指定替换列表索引的元素
        redisTemplate.opsForList().set(key,2,100);

        return "设置成功";
    }

    @RequestMapping(value = "/getList", method = RequestMethod.GET)
    public List getList(@RequestParam @Valid @NotNull(message = "未知的key") String key) {

        /*List x = (List) redisTemplate.opsForList().range(key,0,100);*/
        redisTemplate.opsForList().rightPop(key+"if",222,TimeUnit.SECONDS);

        return null;
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
