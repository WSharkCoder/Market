package cn.wsharkcoder.marcket.service;

import cn.wsharkcoder.marcket.dataobject.Notice;

import java.util.List;

/**
 * Created By 方俊雄
 *
 * @Date Date:2019/7/25 Time:  9:46
 */
public interface NoticeService {
    int saveNotice(Notice notice);
    List<Notice> findBySellerName(String sellerName);
    List<Notice> findByBuyerName(String buyerName);
    int UpdateStatus(int id,int status);
}
