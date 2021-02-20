package com.job_.schedule;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * @author haoxl
 * @Title:
 * @Package
 * @Description:
 * @date 2021/2/20 16:20
 */
@Configuration
@EnableAsync
public class AsyncConfig {
    private int corePoolSize = 10;
    private int maxPoolSize = 200;
    private int queueCapacity = 10;

    @Bean
    public Executor taskExecutor() {
        // 配置线程池 -- 辅助scheduled job 异步，在不同的线程执行
        // 结合 @Async 异步注解，加在方法上，方法异步，加在类上(类下方法都异步执行)
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setQueueCapacity(queueCapacity);
        executor.initialize();
        return executor;
    }

}
