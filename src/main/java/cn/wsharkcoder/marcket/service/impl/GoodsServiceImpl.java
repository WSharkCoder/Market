package cn.wsharkcoder.marcket.service.impl;

import cn.wsharkcoder.marcket.dataobject.Goods;
import cn.wsharkcoder.marcket.repository.GoodsRepository;
import cn.wsharkcoder.marcket.repository.UserRepository;
import cn.wsharkcoder.marcket.Enums.GoodsStatusAndCategoryEnum;
import cn.wsharkcoder.marcket.Enums.ResultEnum;
import cn.wsharkcoder.marcket.service.GoodsService;
import cn.wsharkcoder.marcket.utils.ConditionJdg;
import cn.wsharkcoder.marcket.utils.RdmNumberUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created By 方俊雄
 *
 * @date 2019/7/21 4:15
 */
@Service
@Slf4j
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    private GoodsRepository goodsRepository;
    @Autowired
    private UserRepository userRepository;
    //返回出售商品的条数
    private int ListLength=10;

    @Override
    public List<Goods> findAll() {
        //查找所有物品信息
        List<Goods> list=goodsRepository.findAll();
        return list;
    }
    //返回固定条数在出售商品
    @Override
    public List<Goods> findGoodsOnSale() {
        List<Goods> list=goodsRepository.findAllByStatus(GoodsStatusAndCategoryEnum.GOODS_STATUS_ONE.getCode());
        if(list.size()<ListLength){
            return list;
        }
        List<Goods> list1=new ArrayList<>();
        int[] index= RdmNumberUtil.rdmNumbers(list.size(),ListLength);
        for(int i=0;i<ListLength;i++)
        {
            list1.add(list.get(index[i]));
        }
        return list1;
    }
//添加商品
    @Override
    public int InsertGoods(Goods goods){
        //判断信息是否残缺
        if(ConditionJdg.GoodsInfoIsEmpty(goods)){
            log.error("【Goods Service层】{}", ResultEnum.PARAMETER_EMPTY.getMessage());
            return ResultEnum.PARAMETER_EMPTY.getCode();
        }
//        //判断该用户是否发布同名的商品
//        List<Goods> goodsList=goodsRepository.findALLByUserName(goods.getUserName());
//        for(Goods goods1: goodsList){
//            if(goods1.getName().equals(goods.getName())){
//                log.error("【Goods Service层】{}",ResultEnum.Goods_IS_EXISTED.getMessage());
//                return ResultEnum.Goods_IS_EXISTED.getCode();
//            }
//        }
        //将数据存入数据库

        Goods  goods1=  goodsRepository.save(goods);
        return goods1.getId();
    }
    @Override
    public int updateGoodsInfo(Goods goods) {
        if(ConditionJdg.GoodsInfoIsEmpty(goods)){
            log.error("【Goods Service层】{}", ResultEnum.PARAMETER_EMPTY.getMessage());
            return ResultEnum.PARAMETER_EMPTY.getCode();
        }
        //查找用户信息
        try{
            Goods goods1=goodsRepository.findById(goods.getId()).get();
            goods.setCreateTime(goods1.getCreateTime());
            goods.setUpdateTime(goods1.getUpdateTime());
            BeanUtils.copyProperties(goods,goods1);
          Goods result=  goodsRepository.save(goods1);
            return  result.getId();
        }catch (Exception e){
            log.error("【Goods Service层】{}",ResultEnum.FORMINFO_ERROR.getMessage());
            return ResultEnum.FORMINFO_ERROR.getCode();
        }

    }

    @Override
    public int deleteGoods(int goodsId) {
        Goods goods=new Goods();
        try{
            goods=goodsRepository.findById(goodsId).get();
        }catch(Exception e){
            log.error("【Goods Service层】{}",ResultEnum.GOOD_NOT_EXISTED.getMessage());
       return ResultEnum.GOOD_NOT_EXISTED.getCode();
        }
        goodsRepository.delete(goods);
    return ResultEnum.OPERATION_SUCCESS.getCode();
    }


    @Override
    public List<Goods> findGoodsByUserName(String username) {
        //判断传入的用户名是否为空
        List<Goods> list=goodsRepository.findALLByUserName(username);
        return list;

    }

    @Override
    public Goods findOne(Integer id) {
        Goods goods=new Goods();
        try{
            goods=goodsRepository.findById(id).get();
            return goods;
        }catch(Exception e){
            log.error("【Goods Service层】{}",ResultEnum.GOOD_NOT_EXISTED.getMessage());
        }
        return null;
    }

    @Override
    public int updateGoodsStatus(int goodId,int goodsStatus) {
        Goods goods=new Goods();
        try{
            goods=goodsRepository.findById(goodId).get();
           if(goods.getStatus()!=goodsStatus){
               goods.setStatus(goodsStatus);
               goodsRepository.save(goods);
           }
        }catch(Exception e){
        log.error("【Goods Service层】{}",ResultEnum.GOOD_NOT_EXISTED.getMessage());
        return ResultEnum.GOOD_NOT_EXISTED.getCode();
        }
        return ResultEnum.OPERATION_SUCCESS.getCode();
    }

    @Override
    public List<Goods> findByCategory(String category) {
      List<Goods> list=goodsRepository.findAllByCategory( category );
      return list;
    }
}
