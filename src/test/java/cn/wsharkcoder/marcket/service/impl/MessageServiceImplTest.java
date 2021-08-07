package cn.wsharkcoder.marcket.service.impl;

import cn.wsharkcoder.marcket.dataobject.Messages;
import cn.wsharkcoder.marcket.service.MessageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created By 方俊雄
 *
 * @Date Date:2019/7/24 Time:  17:22
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class MessageServiceImplTest {
    @Autowired
    private MessageService messageService;
    @Test
    public void findMessage() {
        List<Messages> messagesList;
        messagesList = messageService.findMessage(10001);
        for(Messages messages:messagesList){
            System.out.println(messages);
        }
    }
    @Test
    public void Save(){

    }
}