package cn.wsharkcoder.marcket.repository;

import cn.wsharkcoder.marcket.dataobject.Collections;
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
 * @date 2019/7/24 9:40
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CollectionsRepositoryTest {
    @Autowired
    private CollectionsRepository collectionsRepository;

    @Test
    public void findAllByUsername() {
        List<Collections> collections=collectionsRepository.findAllByUsername("张三");
        Assert.assertNotEquals(0,collections.size());
    }
}