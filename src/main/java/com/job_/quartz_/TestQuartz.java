package com.job_.quartz_;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * @author haoxl
 * @Title:
 * @Package
 * @Description:
 * @date 2021/2/20 16:51
 */
public class TestQuartz extends QuartzJobBean {

    /**
     * 执行定时任务
     *
     * @param jobExecutionContext
     * @throws JobExecutionException
     */
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("execute quartz job: " + System.currentTimeMillis() + "    " + Thread.currentThread().getName());
    }
}
