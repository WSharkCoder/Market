package cn.wsharkcoder.marcket.utils;

import cn.wsharkcoder.marcket.dataobject.Activity;
import cn.wsharkcoder.marcket.dataobject.Goods;
import cn.wsharkcoder.marcket.dataobject.Notice;
import cn.wsharkcoder.marcket.dataobject.User;

/**
 * Created By 方俊雄
 *
 * @date 2019/7/21 5:32
 */
public class ConditionJdg {
    public static boolean GoodsInfoIsEmpty(Goods goods) {
        if (goods.getName().isEmpty() || goods.getUserName().isEmpty() || goods.getPlace().isEmpty() || goods.getPrice() == null || goods.getCategory().isEmpty()) {
            return true;
        }
        return false;
    }

    public static boolean ActivityInfoIsEmpty(Activity activity) {
        boolean a =activity.getName().isEmpty();
        boolean b =activity.getNumbers()==null;
        boolean c =activity.getDate().isEmpty();
        boolean d =activity.getAddress().isEmpty();
        boolean e=activity.getEndTime().isEmpty();
        boolean f=activity.getStartTime().isEmpty();
        boolean g=activity.getSponsor().isEmpty();
        boolean h=activity.getStatus()==null;
        boolean i=activity.getAddress().isEmpty();
        if(a||b||c||d||e||f||g||h||i){return true;}
        return false;
    }
    public static boolean UpdateUserInfoFormISEnough(User user){
        if(user.getAddress().isEmpty()||user.getAddress()==null||
                user.getPassword().isEmpty()||user.getPassword()==null||
                user.getImgUrl().isEmpty()||user.getImgUrl()==null||
                user.getContactInfo().isEmpty()||user.getContactInfo()==null)return true;
        return false;
    }
    public static  boolean NoticISEMPTY(Notice notice){
        if(notice.getGoodsId()==null||
        notice.getBuyerName()==null||notice.getBuyerName().isEmpty()||
        notice.getSellerName()==null||notice.getSellerName().isEmpty()||
        notice.getStatus()==null){return true;}
        return false;
    }

}
