import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.chen.ProjectApplication;
import com.chen.mongoBean.DemoBean;
import lombok.Data;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//声明这是spring 的测试类
@RunWith(SpringJUnit4ClassRunner.class)
//定位spring 的配置文件
@SpringBootTest(classes= ProjectApplication.class)
public class TestDemo {

    @Resource
    private MongoTemplate mongoTemplate;

    @Test
    public void test(){
        Integer[] intArr = new Integer[]{1,2,3,4,5};
        Double[] doubleArr = new Double[]{6.0,7.1,8.2,9.3};
        String[] stringArr = new String[]{"str1","str2","str3",};


        printArr(intArr);
        printArr(doubleArr);
        printArr(stringArr);
    }

    public static<T> void printArr(T[] objArr){
        for(int i=0; i< objArr.length; i++){
            System.out.print(objArr[i] + "  ");
        }
        System.out.println();
    }

    @Data
    static class GroupList{
        private String app;
        private String env;
        private String dataType;
        private Integer num;

        public GroupList(String app, String env, String dataType, Integer num) {
            this.app = app;
            this.env = env;
            this.dataType = dataType;
            this.num = num;
        }
    }
}
