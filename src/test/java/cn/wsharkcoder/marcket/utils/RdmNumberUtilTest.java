package cn.wsharkcoder.marcket.utils;

import org.junit.Test;

/**
 * Created By 方俊雄
 *
 * @date 2019/7/21 5:03
 */
public class RdmNumberUtilTest {

    @Test
    public void rdmNumbers() {
       int[] a= RdmNumberUtil.rdmNumbers(6,3);
        for (int t:a
             ) {
            System.out.println(t);

        }
    }
}