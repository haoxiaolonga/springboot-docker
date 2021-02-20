package com.job_.schedule;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author haoxl
 * @Title:
 * @Package
 * @Description:
 * @date 2021/2/20 16:07
 */
@Component
@Async
public class TestScheduledAnnotation {

    /**
     * scheduled默认会在同一个线程中串行执行
     * Async 异步注解
     * cron表达式详解
     * 一个cron表达式有至少6个（也可能7个）有空格分隔的时间元素。按顺序依次为：
     * 秒（0~59） 分钟（0~59） 小时（0~23） 天（0~31） 月（0~11） 星期（1~7 1=SUN 或 SUN。。。） 年份
     */
    @Scheduled(cron = "0/5 * * * * *")
    public void scheduledCron() {
        System.out.println("scheduled cron: " + System.currentTimeMillis() + "" + Thread.currentThread().getName());
    }

    @Scheduled(fixedRate = 5000L)
    public void scheduledFixedRate() {
        System.out.println("scheduled fixedRate: " + System.currentTimeMillis() + "" + Thread.currentThread().getName());
    }

    @Scheduled(fixedDelay = 5000L)
    public void scheduledFixedDelay() {
        System.out.println("scheduled fixedDelay: " + System.currentTimeMillis() + "" + Thread.currentThread().getName());
    }
}
