package cn.wsharkcoder.marcket.repository;

import cn.wsharkcoder.marcket.dataobject.Shoppingcart;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created By 方俊雄
 *
 * @date 2019/7/23 19:42
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ShoppingcartRepositoryTest {
    @Autowired
    private ShoppingcartRepository shoppingcartRepository;
    @Test
    public void findall(){
        List<Shoppingcart> list= shoppingcartRepository.findAll();
        Assert.assertNotEquals(0,list);
        for(Shoppingcart shoppingcart : list){
            System.out.println(shoppingcart);

        }

    }
    @Test
    public void test(){
        List<Shoppingcart> list= shoppingcartRepository.findAllByUsername("张三");
    }


}