package cn.wsharkcoder.marcket.service.impl;

import cn.wsharkcoder.marcket.dataobject.Messages;
import cn.wsharkcoder.marcket.repository.MessagesRepository;
import cn.wsharkcoder.marcket.Enums.ResultEnum;
import cn.wsharkcoder.marcket.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

/**
 * Created By 方俊雄
 *
 * @Date Date:2019/7/24 Time:  17:15
 */
@Service
@Slf4j
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessagesRepository messagesRepository;

    @Override
    public List<Messages> findMessage(Integer id) {
        List<Messages> messages = messagesRepository.findAllByGoodsId(id);
        int i = 0;
        int j = messages.size() - 1;
        while (i < j) {
            Messages messages1 = messages.get(i);
            messages.set(i, messages.get(j));
            messages.set(j, messages1);
            i++;
            j--;
        }
        return messages;
    }

    @Override
    public int SaveMessages(int id,String message) {
        if (message == null || message.isEmpty()) {
            log.error("【Message Service层】{}", ResultEnum.MESSAGE_NULL.getMessage());
            return ResultEnum.MESSAGE_NULL.getCode();
        }
        Messages messages=new Messages(id,message);
        messagesRepository.save(messages);
        return ResultEnum.OPERATION_SUCCESS.getCode();
    }
}
