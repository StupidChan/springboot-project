package com.chen.controller;

import ch.qos.logback.classic.Logger;
import cn.hutool.core.date.DateUtil;
import com.chen.mysqlEntity.DemoEntity;
import com.chen.mapper.DemoMapper;
import com.chen.mongoBean.DemoBean;
import com.chen.mongoBean.ScheduleBean;
import com.chen.utils.C3p0Utils;
import com.chen.utils.LogFileName;
import com.chen.utils.LoggerUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.sql.*;
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

    private final static Logger logger = (Logger) LoggerFactory.getLogger(DemoController.class);
    private static final Logger sys_Log = (Logger) LoggerUtils.Logger(LogFileName.SYS_LOG);
    private static final Logger biz_Log = (Logger) LoggerUtils.Logger(LogFileName.BIZ_LOG);

    @ApiOperation(value = "mysql保存")
    @PostMapping("/mysqlSave")
    public String mysqlSave(){
        DemoEntity demoEntity = new DemoEntity();
        demoEntity.setHouseNum("4-902");
        demoEntity.setAddress("chengxi");
        demoEntity.setCheckInTime(DateUtil.formatDate(new Date()));
        demoMapper.insert(demoEntity);

        logger.info("我是$$$$$$$$$$$$$$$$$$$$$");
        sys_Log.info("我是系统日志");
        biz_Log.info("我是业务日志");

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

    @ApiOperation(value = "c3p0保存")
    @PostMapping("/c3p0Test")
    public String c3p0Test(){
        Connection connection = null;
        Statement statement = null;
        PreparedStatement ps = null;
        try {
            connection = C3p0Utils.getConnection();
            statement = connection.createStatement();

            String sql = "insert into tb_test(time,remark) values(?,?)";
            //3.必须在自定义的connection类中重写prepareStatement(sql)方法
            ps = connection.prepareStatement(sql);
            ps.setString(1, DateUtil.format(new Date(),"yyyy-MM-dd HH:mm:ss"));
            ps.setString(2, "1234");
            int rows = ps.executeUpdate();
            System.out.println("rows:"+rows);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            C3p0Utils.close(connection,statement);
        }

        return "OK";
    }

    @ApiOperation(value = "mongo保存定时任务表")
    @PostMapping("/saveSchedule")
    public String saveSchedule(@RequestBody ScheduleBean scheduleBean){
        mongoTemplate.save(scheduleBean,"tb_schedule");
        return "OK";
    }

    @ApiOperation(value = "单纯测试")
    @PostMapping("/onlyTest")
    public String onlyTest(){
        return "OK";
    }

}
