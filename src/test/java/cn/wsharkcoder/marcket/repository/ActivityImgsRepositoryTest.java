package cn.wsharkcoder.marcket.repository;

import cn.wsharkcoder.marcket.dataobject.ActivityImg;
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
 * @date 2019/7/22 23:19
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ActivityImgsRepositoryTest {
    @Autowired
    private ActivityImgsRepository activityImgsRepository;
    @Test
    public void findAllByActivityId() {
        List<ActivityImg> list= activityImgsRepository.findAllByActivityId(3);
        Assert.assertNotEquals(0, list.size());
        for(ActivityImg activityImg:list){
            System.out.println(activityImg);
        }
    }
}