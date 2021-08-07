package cn.wsharkcoder.marcket.service.impl;

import cn.wsharkcoder.marcket.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created By 方俊雄
 *
 * @date 2019/7/20 13:33
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {
    @Autowired
    private UserService userService;
    private String username="lisi";
    private String password="12121";

    @Test
    public void findPsd() {
        int code=userService.checkPsd(username,password);
        System.out.println(code);

    }
    @Test
    public void SaveCollection(){
        userService.CollectGoods(10001,"张三");
    }
}