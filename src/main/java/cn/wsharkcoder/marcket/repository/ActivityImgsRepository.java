package cn.wsharkcoder.marcket.repository;

import cn.wsharkcoder.marcket.dataobject.ActivityImg;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created By 方俊雄
 *
 * @date 2019/7/22 23:18
 */
public interface ActivityImgsRepository extends JpaRepository<ActivityImg,Integer> {
    List<ActivityImg> findAllByActivityId(int activityid );
}
