package cn.wsharkcoder.marcket.service.impl;

import cn.wsharkcoder.marcket.dataobject.Activity;
import cn.wsharkcoder.marcket.dataobject.Goods;
import cn.wsharkcoder.marcket.service.CollectionsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created By 方俊雄
 *
 * @date 2019/7/24 10:03
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class CollectionsServiceImplTest {
@Autowired
private CollectionsService collectionsService;
    @Test
    public void findCollectGoodsByUserName() {
        List<Activity> activityList=collectionsService.findCollectActivityByUserName("张三");
        System.out.println();
    }
    @Test
    public void findCollectActivityByUserName() {
        List<Goods> list=collectionsService.findCollectGoodsByUserName("张三");
        System.out.println();

    }
}