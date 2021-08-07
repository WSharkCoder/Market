package cn.wsharkcoder.marcket.repository;

import cn.wsharkcoder.marcket.dataobject.Messages;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created By 方俊雄
 *
 * @Date Date:2019/7/24 Time:  17:05
 */
public interface MessagesRepository extends JpaRepository<Messages,Integer> {
    List<Messages> findAllByGoodsId(Integer goodsId);
}
