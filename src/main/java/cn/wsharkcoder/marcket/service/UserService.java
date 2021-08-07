package cn.wsharkcoder.marcket.service;

import cn.wsharkcoder.marcket.dataobject.Goods;
import cn.wsharkcoder.marcket.dataobject.Participate;
import cn.wsharkcoder.marcket.dataobject.User;

import java.util.List;

/**User Service层次
 * Created By 方俊雄
 *
 * @date 2019/7/20 12:07
 */

public interface UserService {
    /**通过Username查询用户密码*/
   int checkPsd(String username,String password);
    /**注册用户*/
   int registerUser(User user);
   String findImgUrl(String username);
   String  findContentInfo(String username);
   List<Participate> findActivity(String username);
   User findOne(String username);
   //收藏商品
int   CollectGoods(int id,String username);
int deleteCollectGoods(int id ,String username);
List<Goods> showCollections(String username);
int updateUserInfo(User user);
}
