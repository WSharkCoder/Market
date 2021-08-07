package cn.wsharkcoder.marcket.repository;

import cn.wsharkcoder.marcket.dataobject.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**User表单DAO层测试
 * Created By 方俊雄
 *
 * @date 2019/7/20 11:12
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;
    private String username="张三";
    /**
     * 增用户
     *
     */
    @Test
    public void insertUserTest(){
        User user=new User();
        user.setUserName("李四");
        user.setPassword("123456");
       User user1= userRepository.save(user);
        Assert.assertEquals(user.getUserName(),user1.getUserName());
    }
    /**
     * 查询用户并修改用户信息
     *
     */
    @Test
    public void findOneTest(){
        User user=userRepository.findById(username).get();
        Assert.assertNotNull(user);
        user.setAddress("上海");
        user.setPassword("125679");
        User user1=userRepository.save(user);
        Assert.assertEquals(user1.getAddress(),"上海");
    }
    /**
     * 删除用户
     */
    @Test
    public void deleteOneTest(){
        User user=userRepository.findById("李四").get();
       userRepository.delete(user);
    }
    /**
     * 判断用户不存在
     */
    @Test
    public void findOne(){
        User user1=userRepository.getOne("李四");
        Assert.assertNotEquals(null,user1.getUserName());
    }


}