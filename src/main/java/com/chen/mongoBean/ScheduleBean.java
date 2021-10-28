package com.chen.mongoBean;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("tb_schedule")
public class ScheduleBean {

    private String app;

    private String env;

    private String pattern;
}
