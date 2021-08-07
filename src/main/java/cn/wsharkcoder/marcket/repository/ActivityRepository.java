package cn.wsharkcoder.marcket.repository;

import cn.wsharkcoder.marcket.dataobject.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created By 方俊雄
 *
 * @date 2019/7/20 20:29
 */
public interface ActivityRepository extends JpaRepository<Activity,Integer> {
    List<Activity> findAllBySponsor(String name);


}
