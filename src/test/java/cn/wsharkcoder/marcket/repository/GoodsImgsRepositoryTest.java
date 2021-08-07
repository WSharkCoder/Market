package cn.wsharkcoder.marcket.repository;

import cn.wsharkcoder.marcket.dataobject.GoodsImg;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created By 方俊雄
 *
 * @date 2019/7/22 17:08
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class GoodsImgsRepositoryTest {
    @Autowired
    private GoodsImgsRepository goodsImgsRepository;
    @Test
    public void findAll(){
        List<GoodsImg> list=goodsImgsRepository.findGoodsImgsByGoodsId(10001);

    }

}