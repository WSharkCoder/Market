package cn.wsharkcoder.marcket.repository;

import cn.wsharkcoder.marcket.Enums.GoodsStatusAndCategoryEnum;
import cn.wsharkcoder.marcket.dataobject.Goods;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

/**goods DAO层
 * Created By 方俊雄
 *
 * @date 2019/7/20 18:32
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class GoodsRepositoryTest {
    @Autowired
    private GoodsRepository goodsRepository;
    private int  id=1;

    /**
     * 增加数据
     */
    @Test
    public void insertGoods(){
        Goods goods=new Goods();
        goods.setName("红米5");
        goods.setPrice(new BigDecimal(3));
        goods.setDetail("红米note5,购买了半年");
        goods.setImgUrl("www.baidu.com");
        goods.setUserName("李四");
        goods.setStatus(1);
        goods.setPlace("huangpi");
        goods.setCategory("电话");
         Goods goods1=goodsRepository.save(goods);
        Assert.assertNotNull(goods1);
    }
    /**
     *更新数据
     */
    @Test
    public void updateGoods(){
        Goods goods=new Goods();
        try {
             goods = goodsRepository.findById(id).get();
             goods.setName("");
            Goods result= goodsRepository.save(goods);
             Assert.assertNotNull(result);
        }catch(Exception e){

            log.error("【报错】");
        }

    }
    /**
     * 删除数据
     *
     */
    @Test
    public void deleteGoods(){
        try{
            Goods goods=goodsRepository.findById(id).get();
            goodsRepository.delete(goods);
            log.info("【数据已经删除】");
        }catch(Exception e){
            log.error("操作出问题了");
        }
    }
    /**
     * 通过用户名查找商品列表
     *
     */
    @Test
    public void findByUserName(){
        List<Goods> list =goodsRepository.findALLByUserName("李四");
        for(Goods good :list){
            System.out.println(good);
        }
    }
    @Test
    public void findByCategory(){
        List<Goods> list=goodsRepository.findAllByCategory(GoodsStatusAndCategoryEnum.CATEGORY_ONE.getMsg());
        System.out.println(list.size());
    }





}