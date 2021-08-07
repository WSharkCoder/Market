package cn.wsharkcoder.marcket.service;

import cn.wsharkcoder.marcket.dataobject.Goods;

import java.util.List;

/**
 * Created By 方俊雄
 *
 * @date 2019/7/21 4:03
 */
public interface GoodsService {
//查找所有物品信息
List<Goods> findAll();
//查询所有出售商品
List<Goods> findGoodsOnSale();
//查看自己发布的物品信息
List<Goods> findGoodsByUserName(String username);
//通过id值查询goods值
Goods findOne(Integer id);
//添加新的商品
    /**
     * 返回商品编号
     * @param goods
     * @return
     */
    int InsertGoods(Goods goods);
//更新物品信息
int updateGoodsInfo(Goods goods);
int deleteGoods(int goodsId);

//5、修改物品状态：售卖中/已完成
int updateGoodsStatus(int goodId,int goodsStatus);
List<Goods>  findByCategory(String category);
}
