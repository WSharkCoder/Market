package cn.wsharkcoder.marcket.utils.converter;

import cn.wsharkcoder.marcket.dataobject.User;
import cn.wsharkcoder.marcket.Form.UserFrom;
import lombok.extern.slf4j.Slf4j;

/**
 * Created By 方俊雄
 *
 * @date 2019/7/20 16:22
 */
@Slf4j
public class UserFrom2UserConverter {
    public static User convert(UserFrom userFrom){
        User user=new User();
        user.setUserName(userFrom.getUsername());
        user.setPassword(userFrom.getPassword());
        return user;
    }

}
