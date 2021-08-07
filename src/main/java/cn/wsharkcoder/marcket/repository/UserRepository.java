package cn.wsharkcoder.marcket.repository;

import cn.wsharkcoder.marcket.dataobject.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created By 方俊雄
 *
 * @date 2019/7/20 11:09
 */
public interface UserRepository extends JpaRepository<User,String> {


}
