package com.job_.quartz_;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author haoxl
 * @Title:
 * @Package
 * @Description:
 * @date 2021/2/20 16:52
 */
@Configuration
public class QuartzConfig  {

    @Bean
    public JobDetail teatQuartzDetail(){
        return JobBuilder.newJob(TestQuartz.class).withIdentity("testQuartz").storeDurably().build();
    }

    @Bean
    public Trigger testQuartzTrigger(){
        //设置时间周期单位秒
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                                                                     .withIntervalInSeconds(10)
                                                                     .repeatForever();
        return TriggerBuilder.newTrigger().forJob(teatQuartzDetail())
                             .withIdentity("testQuartz")
                             .withSchedule(scheduleBuilder)
                             .build();
    }

}
