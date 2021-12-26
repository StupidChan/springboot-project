package com.chen.service;

import cn.hutool.core.date.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Date;

//添加Runnable接口实现类，被定时任务线程池调用

public class SchedulingRunnable implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(SchedulingRunnable.class);

    private String app;
    private String env;

    public SchedulingRunnable(String app, String env) {
        this.app = app;
        this.env = env;
    }

    @Override
    public void run() {
//        logger.info("定时任务开始执行 - app：{}，env：{}", app, env);
//        long startTime = System.currentTimeMillis();
//
//        try {
//
//            //具体定时业务
//            System.out.println(DateUtil.format(new Date(),"yyyy-MM-dd HH:mm:ss") + " 执行定时任务 " + app + "、" + env);
//
//        } catch (Exception ex) {
//            logger.error(String.format("定时任务执行异常 - bean：%s，方法：%s", app, env), ex);
//        }
//
//        long times = System.currentTimeMillis() - startTime;
//        logger.info("定时任务执行结束 - bean：{}，方法：{}，耗时：{} 毫秒", app, env, times);
    }

}