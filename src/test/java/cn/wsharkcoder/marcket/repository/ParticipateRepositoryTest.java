package cn.wsharkcoder.marcket.repository;

import cn.wsharkcoder.marcket.dataobject.Participate;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created By 方俊雄
 *
 * @date 2019/7/20 22:43
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ParticipateRepositoryTest {
    @Autowired
    private ParticipateRepository participateRepository;
    @Test
    public void create(){
        Participate participate=new Participate();
        participate.setActivityAddress("操场");
        participate.setActivityId(12121);
        participate.setActivityName("哈啊哈哈");
        participate.setDate("19-12-12");
        participate.setPromoter_name("张三");
        participate.setUserName("李四");
       Participate participate1 =participateRepository.save(participate);
        Assert.assertNotNull(participate1);
    }
}