package cn.wsharkcoder.marcket.service.impl;

import cn.wsharkcoder.marcket.service.ActivityImgService;
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
 * @date 2019/7/22 23:28
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ActivityImgServiceImplTest {
    @Autowired
    private ActivityImgService activityImgService;
    @Test
    public void getImgs() {
        List<String> list=activityImgService.getImgs(21212);

        for (String s:list
             ) {
            System.out.println(s);

        }
    }
    @Test
    public void saveImgs(){
        List<String> list=new ArrayList<>();
        list.add("12312");
        list.add("asasas");
        activityImgService.saveImgs(12,list);
    }
}