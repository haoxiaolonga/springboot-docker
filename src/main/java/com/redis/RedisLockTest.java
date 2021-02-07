package com.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sun.text.resources.FormatData;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * @author haoxl
 * @Title:
 * @Package
 * @Description:
 * @date 2021/2/7 10:01
 */
@RestController("test lock")
@RequestMapping("/redisTest")
@Slf4j
public class RedisLockTest {

    @Autowired
    private RedisLock redisLock;

    @PostMapping(value = "/setLock")
    public boolean testTryGetLock(@RequestParam String key, HttpServletRequest request) {

        long value = 10;
        boolean status = redisLock.tryGetDistributedLock(key, value, 1080L);
        if (status) {
            System.out.println("hello -- get lock，value :".concat("10"));
        } else {
            System.out.println("no -- don^t get lock");
        }
        return status;
    }

    @PostMapping("/deleteLock")
    public boolean testTryDelLock(@RequestParam String key, @RequestParam String value) {

        boolean status = redisLock.releaseDistributedLock(key, value);
        if (status) {
            System.out.println("hello -- delete lock");
        } else {
            System.out.println("no -- delete lock fail");
        }
        return status;
    }

}
