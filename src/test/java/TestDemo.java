import cn.hutool.core.date.DateUtil;
import com.chen.ProjectApplication;
import com.chen.mongoBean.DemoBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

//声明这是spring 的测试类
@RunWith(SpringJUnit4ClassRunner.class)
//定位spring 的配置文件
@SpringBootTest(classes= ProjectApplication.class)
public class TestDemo {

    @Resource
    private MongoTemplate mongoTemplate;

    @Test
    public void test(){
        DemoBean demoBean = new DemoBean();
        demoBean.setHouseNum("4-902");
        demoBean.setAddress("chengxi");
        demoBean.setCheckInTime("2020-07-12");

        System.out.println(DateUtil.date());
        for(int i=0; i<5000000; i++){
            mongoTemplate.save(demoBean,"data_home");
        }
        System.out.println(DateUtil.date());

    }
}
