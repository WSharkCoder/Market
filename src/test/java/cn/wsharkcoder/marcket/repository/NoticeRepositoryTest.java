package cn.wsharkcoder.marcket.repository;

import cn.wsharkcoder.marcket.dataobject.Notice;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created By 方俊雄
 *
 * @Date Date:2019/7/25 Time:  9:39
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class NoticeRepositoryTest {
    @Autowired
    private  NoticeRepository noticeRepository;
    @Test
    public void Save(){
        Notice notice=new Notice(10001,"张三","李四",0);
        noticeRepository.save(notice);
    }

    @Test
    public void findAllBySellerName() {
        List<Notice> list=noticeRepository.findAllBySellerName("张三");
    }

    @Test
    public void findAllByBuyerName() {
    }
}