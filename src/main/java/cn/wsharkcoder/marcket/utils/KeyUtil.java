package cn.wsharkcoder.marcket.utils;

import java.util.Random;

/**
 * Created By 方俊雄
 *
 * @date 2019/7/20 18:51
 */
public class KeyUtil {
    /***
     * 时间+随机数生成主键
     */
    public static synchronized String genUniqueKey(){
        Random random=new Random();
        Integer number=random.nextInt(900000)+100000;

        return System.currentTimeMillis()+String.valueOf(number);
    }
}
