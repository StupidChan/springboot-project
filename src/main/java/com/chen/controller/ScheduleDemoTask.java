package com.chen.controller;

import cn.hutool.core.bean.BeanUtil;
import com.chen.mongoBean.ScheduleBean;
import com.chen.registrar.CronTaskRegistrar;
import com.chen.service.SchedulingRunnable;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(description  = "定时任务接口")
@RestController
public class ScheduleDemoTask {

    @Autowired
    private CronTaskRegistrar cronTaskRegistrar;
    @Autowired
    private MongoTemplate mongoTemplate;

    @ApiOperation(value = "新增编辑定时任务")
    @PostMapping("/saveOrUpdateSchedule")
    public String taskWithParams(ScheduleBean scheduleBean) {

        Query query = new Query(Criteria.where("app").is(scheduleBean.getApp()).and("env").is(scheduleBean.getEnv()));
        ScheduleBean bean = mongoTemplate.findOne(query,ScheduleBean.class);
        if (BeanUtil.isNotEmpty(bean)) {
            SchedulingRunnable task = new SchedulingRunnable(scheduleBean.getApp(),scheduleBean.getEnv());
            cronTaskRegistrar.removeCronTask(task);
        }

        SchedulingRunnable task = new SchedulingRunnable(scheduleBean.getApp(),scheduleBean.getEnv());
        cronTaskRegistrar.addCronTask(task, scheduleBean.getPattern());

        return "ok";
    }
}
