package cn.wsharkcoder.marcket.service.impl;

import cn.wsharkcoder.marcket.dataobject.Notice;
import cn.wsharkcoder.marcket.repository.NoticeRepository;
import cn.wsharkcoder.marcket.Enums.ResultEnum;
import cn.wsharkcoder.marcket.service.NoticeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created By 方俊雄
 *
 * @Date Date:2019/7/25 Time:  9:51
 */
@Service
@Slf4j
public class NoticeServiceImpl implements NoticeService {
    @Autowired
    private NoticeRepository noticeRepository;
    @Override
    public int saveNotice(Notice notice) {
        Notice notice1=noticeRepository.save(notice);
        return ResultEnum.OPERATION_SUCCESS.getCode();
    }

    @Override
    public List<Notice> findBySellerName(String sellerName) {
        List<Notice> noticeList=noticeRepository.findAllBySellerName(sellerName);
        return noticeList;
    }

    @Override
    public List<Notice> findByBuyerName(String buyerName) {
        List<Notice> noticeList=noticeRepository.findAllByBuyerName(buyerName);
        return noticeList;
    }

    @Override
    public int UpdateStatus(int id, int status) {
        try{
            Notice notice=noticeRepository.findById(id).get();
            notice.setStatus(status);
            noticeRepository.save(notice);
            return ResultEnum.OPERATION_SUCCESS.getCode();
        }catch(Exception e){
            log.error("【Notice Service层】对应信息不存在");
            return  ResultEnum.OPERATION_FAIL.getCode();

        }

    }
}
