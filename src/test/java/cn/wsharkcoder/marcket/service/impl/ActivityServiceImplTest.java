package cn.wsharkcoder.marcket.service.impl;

import cn.wsharkcoder.marcket.dataobject.Activity;
import cn.wsharkcoder.marcket.service.ActivityService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created By 方俊雄
 *
 * @date 2019/7/21 21:06
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ActivityServiceImplTest {
    @Autowired
    private ActivityService activityService;

    @Test
    public void findAll() {
        List<Activity> activityList=activityService.findAll();
        for(Activity activity:activityList){
            System.out.println(activity);
        }
    }

    @Test
    public void update() {
    }

    @Test
        public void detele() {
        activityService.detele(2);
    }

    @Test
    public void create() {
        Activity activity=new Activity();
        activity.setName("老年散步");
        activity.setAddress("南苑操场");
        activity.setSponsor("张三");
        activity.setStatus(0);
        activity.setNumbers(12);
        activity.setDate("123123");
        activity.setEndTime("2019-1-19");
        activity.setStartTime("2019-1-1");
       int t= activityService.create(activity);
        Assert.assertNotNull(t);

    }

    @Test
    public void join() {
        List<String> userNames=new ArrayList<>();
        userNames.add("zhangsan");
        userNames.add("kisisa");
        activityService.join(4, userNames);
    }
    @Test
    public void  findBySponer(){
        List<Activity> activityList=activityService.findBySponer("张三");
        Assert.assertNotNull(activityList);
        for(Activity activity:activityList){
            System.out.println(activity);
        }
    }
    @Test
    public void delete(){
        activityService.detele(2);
        log.info("删除成功");
    }
}