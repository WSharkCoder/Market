package cn.wsharkcoder.marcket.repository;

import cn.wsharkcoder.marcket.dataobject.Messages;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created By 方俊雄
 *
 * @Date Date:2019/7/24 Time:  17:06
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MessagesRepositoryTest {
    @Autowired
    private MessagesRepository messagesRepository;


    @Test
    public void findAllByGoodsId() {
        List<Messages> messages=messagesRepository.findAllByGoodsId(10001);
    }
}