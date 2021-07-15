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
        System.out.println(DateUtil.date());

        List<GroupList> groupLists = new ArrayList<>();
        GroupList gl1 = new GroupList("aabb","dev","type1",1);
        GroupList gl2 = new GroupList("aabb","dev","type2",2);
        GroupList gl3 = new GroupList("aabb","dev","type2",1);
        GroupList gl4 = new GroupList("bbcc","dev","type3",3);
        GroupList gl5 = new GroupList("bbcc","dev","type3",6);
        GroupList gl6 = new GroupList("aabb","prod","type3",5);
        GroupList gl7 = new GroupList("aabb","prod","type3",1);
        GroupList gl8 = new GroupList("bbcc","prod","type1",4);
        GroupList gl9 = new GroupList("bbcc","prod","type1",7);
        GroupList gl10 = new GroupList("ccdd","prod","type1",1);
        groupLists.add(gl1);groupLists.add(gl2);groupLists.add(gl3);groupLists.add(gl4);groupLists.add(gl5);
        groupLists.add(gl6);groupLists.add(gl7);groupLists.add(gl8);groupLists.add(gl9);groupLists.add(gl10);

        Map<String, Map<String, Map<String, List<GroupList>>>> groupMap =
                groupLists.stream().collect(
                        Collectors.groupingBy(GroupList::getApp,
                                Collectors.groupingBy(GroupList::getEnv,Collectors.groupingBy(GroupList::getDataType))));

        List<String> apps = groupLists.stream().map(GroupList::getApp).collect(Collectors.toList());
        List<String> envs = groupLists.stream().map(GroupList::getEnv).collect(Collectors.toList());
        List<String> types = groupLists.stream().map(GroupList::getDataType).collect(Collectors.toList());

        System.out.println(JSON.toJSONString(groupMap));
        System.out.println(DateUtil.date());

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
