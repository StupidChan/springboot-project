package com.chen.controller;

import cn.hutool.core.date.DateUtil;
import com.chen.entity.DemoEntity;
import com.chen.mapper.DemoMapper;
import com.chen.mongoBean.DemoBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

@Api(description  = "测试接口")
@RestController
public class DemoController {

    @Resource
    private DemoMapper demoMapper;
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Resource
    private MongoTemplate mongoTemplate;

    @ApiOperation(value = "mysql保存")
    @PostMapping("/mysqlSave")
    public String mysqlSave(){
        DemoEntity demoEntity = new DemoEntity();
        demoEntity.setHouseNum("4-902");
        demoEntity.setAddress("chengxi");
        demoEntity.setCheckInTime(DateUtil.formatDate(new Date()));
        demoMapper.insert(demoEntity);
        return "OK";
    }

    @ApiOperation(value = "redis保存")
    @PostMapping("/redisSave")
    public String redisSave(){
        stringRedisTemplate.opsForValue().set("demo", DateUtil.formatDate(new Date()));
        return "OK";
    }

    @ApiOperation(value = "mongo保存")
    @PostMapping("/mongoSave")
    public String mongoSave(){
        DemoBean demoBean = new DemoBean();
        demoBean.setHouseNum("4-902");
        demoBean.setAddress("chengxi");
        demoBean.setCheckInTime(DateUtil.formatDate(new Date()));

        mongoTemplate.save(demoBean,"data_home");
        return "OK";
    }

}
