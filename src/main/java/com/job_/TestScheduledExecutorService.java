package com.job_;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author haoxl
 * @Title:
 * @Package
 * @Description:
 * @date 2021/2/20 15:40
 */
public class TestScheduledExecutorService {
    // 是基于线程池设计的定时任务类,每个调度任务都会分配到线程池中的一个线程去执行,也就是说,任务是并发执行,互不影响

    public static void main(String[] args) {

        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

        //单一执行任务
        scheduledExecutorService.schedule(() -> {
            System.out.println("ScheduledExecutorService 单一执行任务：" + System.currentTimeMillis());
        }, 10L, TimeUnit.SECONDS);

        //周期时间任务
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            System.out.println("ScheduledExecutorService 周期执行任务：" + System.currentTimeMillis());
        }, 10L, 10L, TimeUnit.SECONDS);

        //周期延时任务
        scheduledExecutorService.scheduleWithFixedDelay(() -> {
            System.out.println("ScheduledExecutorService 周期延时执行任务：" + System.currentTimeMillis());
        }, 15L, 15L, TimeUnit.SECONDS);

        //执行多少次后中断
//        scheduledExecutorService.shutdown();
    }

}
