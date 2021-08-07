package cn.wsharkcoder.marcket.service;

import cn.wsharkcoder.marcket.dataobject.Messages;


import java.util.List;

/**
 * Created By 方俊雄
 *
 * @Date Date:2019/7/24 Time:  17:14
 */
public interface MessageService {
    List<Messages> findMessage(Integer id);
    int SaveMessages(int id,String message);
}
