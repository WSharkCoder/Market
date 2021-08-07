package cn.wsharkcoder.marcket.service;

import cn.wsharkcoder.marcket.dataobject.Activity;
import cn.wsharkcoder.marcket.dataobject.Goods;

import java.util.List;

/**
 * Created By 方俊雄
 *
 * @date 2019/7/24 9:44
 */
public interface CollectionsService {
    List<Goods> findCollectGoodsByUserName(String username);
    List<Activity> findCollectActivityByUserName(String username);
    int saveGoodsCollections(int id,String username);
    int saveActivityCollections(int id,String username);
    int deleteGoodsCollections(int id,String username);
    int deleteActivityCollections(int id,String username);
}
