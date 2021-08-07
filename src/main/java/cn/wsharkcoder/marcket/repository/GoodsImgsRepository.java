package cn.wsharkcoder.marcket.repository;

import cn.wsharkcoder.marcket.dataobject.GoodsImg;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created By 方俊雄
 *
 * @date 2019/7/22 17:06
 */
public interface GoodsImgsRepository extends JpaRepository<GoodsImg,Integer> {
    List<GoodsImg> findGoodsImgsByGoodsId(int id);

}
