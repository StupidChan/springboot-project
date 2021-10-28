package com.chen.service;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.chen.mongoBean.ScheduleBean;
import com.chen.registrar.CronTaskRegistrar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DbTaskRunner implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(DbTaskRunner.class);

    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private CronTaskRegistrar cronTaskRegistrar;

    @Override
    public void run(String... args) {
        // 初始加载数据库里状态为正常的定时任务
        Query query = new Query();
        List<ScheduleBean> taskList = mongoTemplate.find(query,ScheduleBean.class);
        if (CollectionUtils.isNotEmpty(taskList)) {
            for (ScheduleBean bean : taskList) {
                SchedulingRunnable task = new SchedulingRunnable(bean.getApp(),bean.getEnv());
                cronTaskRegistrar.addCronTask(task, bean.getPattern());
            }

            logger.info("定时任务已加载完毕...");
        }
    }
}
