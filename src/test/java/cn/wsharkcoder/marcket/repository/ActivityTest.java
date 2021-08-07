package cn.wsharkcoder.marcket.repository;

import cn.wsharkcoder.marcket.dataobject.Activity;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created By 方俊雄
 *
 * @date 2019/7/20 20:36
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ActivityTest {
    /**
     * create activity\
     *
     */
    @Autowired
    private ActivityRepository activityRepository;
    private String username="张三";
    @Test
    public void create(){
        Activity activity=new Activity();
        activity.setName("青春长跑");
        activity.setAddress("北苑操场");
        activity.setDate("1999-23-23");
        activity.setStartTime("1999-1-6");
         activity.setEndTime("12121212");
         activity.setSponsor("张三");
        activity.setNumbers(12);
        activity.setDetail(" ");
        activity.setStatus(0);
     activityRepository.save(activity);
    }

    /**
     * update 活动
     */
    @Test
    public void update(){
        try{
            Activity activity=activityRepository.findById(2).get();
            activity.setNumbers(43);
           Activity activity1= activityRepository.save(activity);
           Assert.assertNotNull(activity);
        }catch (Exception e)
        {
            log.error("【出错了】");
        }
    }

    /**
     * 通过主办方姓名经行查询
     */
    @Test
    public void findAllBySponsor() {
        List<Activity> list=activityRepository.findAllBySponsor(username);
        for(Activity activity: list){
            System.out.println(activity);
        }

    }
}