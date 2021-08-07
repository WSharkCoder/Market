package cn.wsharkcoder.marcket.service.impl;

import cn.wsharkcoder.marcket.dataobject.Goods;
import cn.wsharkcoder.marcket.service.GoodsService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created By 方俊雄
 *
 * @date 2019/7/21 4:18
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class GoodsServiceImplTest {
    @Autowired
    private GoodsService goodsService;

    @Test
    public void findAll() {
       List<Goods> list= goodsService.findAll();
       for(Goods goods:list){
           System.out.println(goods);
       }
    }
    @Test
    public void findGoodsOnsale(){
        List<Goods> list=goodsService.findGoodsOnSale();
        for(Goods goods:list){
            System.out.println(goods);
        }
    }
    @Test
    public void InsertGoods(){
        Goods goods=new Goods();
        goods.setName("红米5");
        goods.setUserName("张三");
        goods.setPlace("huangpi");
        goods.setPrice(new BigDecimal(2));
        goods.setCategory("电话");
       int t= goodsService.InsertGoods(goods);
        System.out.println(t);

    }


}