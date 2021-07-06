package com.chen.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("table_home")
public class DemoEntity {

    private String houseNum;

    private String address;

    private String checkInTime;
}
