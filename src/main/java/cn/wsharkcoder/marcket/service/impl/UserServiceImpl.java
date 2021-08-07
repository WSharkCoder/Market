package cn.wsharkcoder.marcket.service.impl;

import cn.wsharkcoder.marcket.dataobject.Goods;
import cn.wsharkcoder.marcket.dataobject.Participate;
import cn.wsharkcoder.marcket.dataobject.Shoppingcart;
import cn.wsharkcoder.marcket.dataobject.User;
import cn.wsharkcoder.marcket.repository.GoodsRepository;
import cn.wsharkcoder.marcket.repository.ParticipateRepository;
import cn.wsharkcoder.marcket.repository.ShoppingcartRepository;
import cn.wsharkcoder.marcket.repository.UserRepository;
import cn.wsharkcoder.marcket.Enums.ResultEnum;
import cn.wsharkcoder.marcket.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created By 方俊雄
 *
 * @date 2019/7/20 12:10
 *
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ParticipateRepository participateRepository;
    @Autowired
    private ShoppingcartRepository shoppingcartRepository;
    @Autowired
    private GoodsRepository goodsRepository;

    @Override
    @Transactional
    public int  checkPsd(String username,String password) {
        //判断参数是否未空
        if(username.isEmpty()||password.isEmpty()){
            log.error("【User Service层】{}", ResultEnum.PARAMETER_EMPTY.getMessage());
            return ResultEnum.PARAMETER_EMPTY.getCode();
        }
        //判断用户是否存在
        User user=new User();
        try {
           user = userRepository.findById(username).get();
        }catch (Exception e){
            log.info("【User Service层】{}",ResultEnum.USER_NOT_EXIST.getMessage());
            return ResultEnum.USER_NOT_EXIST.getCode();
        }
       if(!password.equals(user.getPassword())){
           log.error("【User Service层】{}",ResultEnum.PASSWORD_ERROR.getMessage());
           return ResultEnum.PASSWORD_ERROR.getCode();
       }
       return ResultEnum.OPERATION_SUCCESS.getCode();
    }

    @Override
    @Transactional
    public int registerUser(User user) {
        //判断username和passord是否为空
        if(user.getPassword().isEmpty()||user.getUserName().isEmpty()){
            log.error("【User Service层】{}",ResultEnum.USERINFO_INCOMPLETE.getMessage());
            return ResultEnum.USERINFO_INCOMPLETE.getCode();
        }
        //判断用户名已经存在
        try{
            User user1=userRepository.findById(user.getUserName()).get();
            log.error("【User Service层】{}",ResultEnum.USER_IS_EXISTED.getMessage());
            return ResultEnum.USER_IS_EXISTED.getCode();
        }catch(Exception e){
        }
        //将数据存入数据库中
         userRepository.save(user);
         return ResultEnum.USERINFO_IN_DATA.getCode();

    }
    @Override
    public String findImgUrl(String username ){
        try{
            User user=userRepository.findById(username).get();
            return user.getImgUrl();
        }catch(Exception e){
            return "";
        }
    }

    @Override
    public String findContentInfo(String username) {
        try{
            User user1=userRepository.findById(username).get();
            return user1.getContactInfo();
        }catch (Exception e)
        {
            log.error("【User Service层】{}",ResultEnum.USER_NOT_EXIST.getMessage());
            return "";
        }
    }

    @Override
    public List<Participate> findActivity(String username) {
        return participateRepository.findAllByUserName(username);
    }

    @Override
    public User findOne(String username) {
        try{
            return userRepository.findById(username).get();
        }catch(Exception e){
            log.info("【User Service层】{}",ResultEnum.USER_NOT_EXIST.getMessage());
            return null;
        }
    }

    @Override
    public int CollectGoods(int id, String username) {
             //判读用户是否有相同的收藏
        List<Shoppingcart> shoppingcarts = shoppingcartRepository.findAllByUsername(username);
        for(Shoppingcart shoppingcart : shoppingcarts){
            if(id== shoppingcart.getGoodsid())return ResultEnum.GOODS_COLLECTED.getCode();
        }
            Shoppingcart shoppingcart =new Shoppingcart();
            shoppingcart.setGoodsid(id);
            shoppingcart.setUsername(username);
            shoppingcartRepository.save(shoppingcart);
            return ResultEnum.OPERATION_SUCCESS.getCode();

    }

    @Override
    public int deleteCollectGoods(int id, String username) {
        List<Shoppingcart> shoppingcarts = shoppingcartRepository.findAllByUsername(username);
        for(Shoppingcart shoppingcart : shoppingcarts){
            if(id== shoppingcart.getGoodsid()) {
                shoppingcartRepository.delete(shoppingcart);
                return ResultEnum.OPERATION_SUCCESS.getCode();
            }
        }
        log.error("【User Service层】{}",ResultEnum.GOODSCOLLERCTION_NOT_EXIST.getMessage());
        return ResultEnum.GOODSCOLLERCTION_NOT_EXIST.getCode();

    }

    @Override
    public List<Goods> showCollections(String username) {
        List<Shoppingcart> list= shoppingcartRepository.findAllByUsername(username);
       List<Goods> goodsList=new ArrayList<>();
        for(Shoppingcart shoppingcart :list){
            Goods goods=goodsRepository.findById(shoppingcart.getGoodsid()).get();
            goodsList.add(goods);
        }
        return goodsList;
    }

    @Override
    public int updateUserInfo(User user) {
        try{
            User user1=userRepository.findById(user.getUserName()).get();
            user1.setPassword(user.getPassword());
            user1.setAddress(user.getAddress());
            user1.setContactInfo(user.getContactInfo());
            user1.setImgUrl(user.getImgUrl());
            userRepository.save(user1);
            return ResultEnum.OPERATION_SUCCESS.getCode();
        }catch(Exception e){
            log.error("【User Service层】{}",ResultEnum.USER_NOT_EXIST.getMessage());
            return ResultEnum.USER_NOT_EXIST.getCode();
        }

    }


}
