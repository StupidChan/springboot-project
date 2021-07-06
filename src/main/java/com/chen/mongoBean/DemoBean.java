package com.chen.mongoBean;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("data_home")
public class DemoBean {

    private String houseNum;

    private String address;

    private String checkInTime;
}
