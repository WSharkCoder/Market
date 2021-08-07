package cn.wsharkcoder.marcket.repository;

import cn.wsharkcoder.marcket.dataobject.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created By 方俊雄
 *
 * @Date Date:2019/7/25 Time:  9:37
 */
public interface NoticeRepository extends JpaRepository<Notice ,Integer> {

    List<Notice> findAllBySellerName(String sellerName);
    List<Notice> findAllByBuyerName(String buyerName);
}
