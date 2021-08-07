package cn.wsharkcoder.marcket;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created By 方俊雄
 *
 * @date 2019/7/20 11:03
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class LogbackTest {
    @Test
    public void LogBackTest(){
        log.info("info。。。");
        log.error("error");
    }
}
