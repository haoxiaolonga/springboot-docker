package com.job_;

import java.time.Instant;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

/**
 * @author haoxl
 * @Title:
 * @Package
 * @Description:
 * @date 2021/2/20 15:35
 */
public class TestTimerJob {

    public static void main(String[] args) {

        // 让你的程序按照某一个频度执行，但不能在指定时间运行。一般用的较少
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                System.out.println("task job run : " + System.currentTimeMillis());
            }
        };

        Timer timer = new Timer();

        // 单线程的，下面是维护的一个数组
        //timer 单一执行
        timer.schedule(timerTask,10000L);

        // timer周期运行，首次是15延迟
//        timer.scheduleAtFixedRate(timerTask,15L,10L);

        // timer周期运行，首次是指定时间启动
//        timer.scheduleAtFixedRate(timerTask, new Date(System.currentTimeMillis() + 2000L),10L);
    }
}
