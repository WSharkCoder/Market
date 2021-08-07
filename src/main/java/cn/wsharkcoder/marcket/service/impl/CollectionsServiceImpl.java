package cn.wsharkcoder.marcket.service.impl;

import cn.wsharkcoder.marcket.dataobject.Activity;
import cn.wsharkcoder.marcket.dataobject.Collections;
import cn.wsharkcoder.marcket.dataobject.Goods;
import cn.wsharkcoder.marcket.repository.ActivityRepository;
import cn.wsharkcoder.marcket.repository.CollectionsRepository;
import cn.wsharkcoder.marcket.repository.GoodsRepository;
import cn.wsharkcoder.marcket.service.CollectionsService;
import cn.wsharkcoder.marcket.Enums.ResultEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created By 方俊雄
 *
 * @date 2019/7/24 9:50
 */

@Service
@Slf4j
public class CollectionsServiceImpl implements CollectionsService {
    @Autowired
    private CollectionsRepository collectionsRepository;
    @Autowired
    private GoodsRepository goodsRepository;
    @Autowired
    private ActivityRepository activityRepository;

    @Override
    public List<Activity> findCollectActivityByUserName(String username) {
        List<Collections> collectionsList = collectionsRepository.findAllByUsername(username);
        List<Activity> list = new ArrayList<>();
        for (Collections collections1 : collectionsList) {
            if (collections1.getActivityId() != null) {
                try {
                     Activity activity= activityRepository.findById(collections1.getActivityId()).get();
                    list.add(activity);
                } catch (Exception e) {
                    log.error("【Collections Service层】{}", ResultEnum.COLLECTIONS_ACTIVITY_NOT_EXIST.getMessage());
                }
            }
        }
        return list;
    }

    @Override
    public int saveGoodsCollections(int id, String username) {
        //判断是否已经收藏
        List<Collections> collectionsList=collectionsRepository.findAllByUsername(username);
        for(Collections collections:collectionsList){
            if(collections.getGoodsId()!=null&&collections.getGoodsId()==id)return ResultEnum.GOODS_COLLECTED_TWICE.getCode();
        }
        Collections collections=new Collections(username,id,null);
        collectionsRepository.save(collections);
        return ResultEnum.OPERATION_SUCCESS.getCode();
    }

    @Override
    public int saveActivityCollections(int id, String username) {
        List<Collections> collectionsList=collectionsRepository.findAllByUsername(username);
        for(Collections collections:collectionsList){
            if(collections.getActivityId()!=null&&collections.getActivityId()==id)return ResultEnum.ACTIVITY_COLLECTED_TWICE.getCode();
        }
        Collections collections=new Collections(username,null,id);
        collectionsRepository.save(collections);

        return ResultEnum.OPERATION_SUCCESS.getCode();
    }

    @Override
    public int deleteGoodsCollections(int id, String username) {
        List<Collections> list=collectionsRepository.findAllByUsername(username);
        for(Collections collections:list){
            if(collections.getGoodsId()!=null&&collections.getGoodsId()==id){
                collectionsRepository.delete(collections);
                return ResultEnum.COLLECTIONS_DELETE_SUCCESS.getCode();
            }
        }
        log.info("【Collections Service层】{}",ResultEnum.COLLECTIONS_DELETE_FAIL);
        return ResultEnum.COLLECTIONS_DELETE_FAIL.getCode();

    }

    @Override
    public int deleteActivityCollections(int id, String username) {
        List<Collections> list=collectionsRepository.findAllByUsername(username);
        for(Collections collections:list){
            if(collections.getActivityId()!=null&&collections.getActivityId()==id){
                collectionsRepository.delete(collections);
                return ResultEnum.COLLECTIONS_DELETE_SUCCESS.getCode();
            }
        }
        log.info("【Collections Service层】{}",ResultEnum.COLLECTIONS_DELETE_FAIL);
        return ResultEnum.COLLECTIONS_DELETE_FAIL.getCode();

    }

    @Override
    public List<Goods> findCollectGoodsByUserName(String username) {
        List<Collections> collectionsList = collectionsRepository.findAllByUsername(username);
        List<Goods> list = new ArrayList<>();
        for (Collections collections1 : collectionsList) {
            if (collections1.getGoodsId() != null) {
                try {
                    Goods goods = goodsRepository.findById(collections1.getGoodsId()).get();
                    list.add(goods);
                } catch (Exception e) {
                    log.error("【Collections Service层】{}", ResultEnum.COLLECTIONS_GOOD_NOT_EXIST.getMessage());
                }
            }
        }
        return list;

    }
}
