package cn.wsharkcoder.marcket.repository;

import cn.wsharkcoder.marcket.dataobject.Collections;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created By 方俊雄
 *
 * @date 2019/7/24 9:39
 */
public interface CollectionsRepository extends JpaRepository<Collections,Integer> {
    List<Collections> findAllByUsername(String username);
}
