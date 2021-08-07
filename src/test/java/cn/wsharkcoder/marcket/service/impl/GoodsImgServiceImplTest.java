package cn.wsharkcoder.marcket.service.impl;

import cn.wsharkcoder.marcket.service.GoodsImgService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created By 方俊雄
 *
 * @date 2019/7/22 17:25
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class GoodsImgServiceImplTest {
    @Autowired
    private GoodsImgService goodsImgService;

    @Test
    public void getImgs() {
        List<String> list=goodsImgService.getImgs(10001);
        for(String s:list){
            System.out.println(s);
        }
    }
    @Test
    public void saveImgs(){
        List<String>list=new ArrayList<>();
        list.add("121212");
        list.add("awsqwqw");
        goodsImgService.saveImgs(1001,list);
    }
}