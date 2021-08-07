package cn.wsharkcoder.marcket.utils;

import java.util.Random;

/**
 * Created By 方俊雄
 *
 * @date 2019/7/21 4:55
 */
public class RdmNumberUtil {
    public static int[] rdmNumbers(int size,int length){
        Random random=new Random();
        int[] numbers=new int[length];
        for(int i=0;i<length;i++){
           numbers[i]=random.nextInt(size);
           for(int j=0;j<i;j++){
               if(numbers[j]==numbers[i]){
                   i--;break;
               }
           }
        }
        return numbers;
    }
}
