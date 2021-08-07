package cn.wsharkcoder.marcket.repository;

import cn.wsharkcoder.marcket.dataobject.Goods;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created By 方俊雄
 *
 * @date 2019/7/20 18:30
 */
public interface GoodsRepository extends JpaRepository<Goods,Integer> {
    List<Goods> findALLByUserName(String Username);
    List<Goods> findAllByStatus(int status);
    List<Goods> findAllByCategory(String category);
}
