package cn.wsharkcoder.marcket.repository;

import cn.wsharkcoder.marcket.dataobject.Shoppingcart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


/**
 * Created By 方俊雄
 *
 * @date 2019/7/23 19:41
 */
public interface ShoppingcartRepository extends JpaRepository<Shoppingcart,Integer> {
    List<Shoppingcart> findAllByUsername(String username);

}
