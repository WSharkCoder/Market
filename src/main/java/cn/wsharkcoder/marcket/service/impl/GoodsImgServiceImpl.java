package cn.wsharkcoder.marcket.service.impl;

import cn.wsharkcoder.marcket.dataobject.GoodsImg;
import cn.wsharkcoder.marcket.repository.GoodsImgsRepository;
import cn.wsharkcoder.marcket.service.GoodsImgService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created By 方俊雄
 *
 * @date 2019/7/22 17:16
 */
@Service
@Slf4j
public class GoodsImgServiceImpl implements GoodsImgService {
    @Autowired
    private GoodsImgsRepository goodsImgsRepository;

    @Override
    public List<String> getImgs(int id) {
        List<GoodsImg> goodsImgList=goodsImgsRepository.findGoodsImgsByGoodsId(id);
        List<String> list=new ArrayList<>();
        for(GoodsImg goodsImg:goodsImgList){
            list.add(goodsImg.getImgUrl());
        }
        return list;
    }

    @Override
    public void saveImgs(int id, List<String> list) {

        System.out.println(list.size());
        for(String img: list){

            System.out.println();
            System.out.println();
            GoodsImg goodsImg=new GoodsImg();
            goodsImg.setGoodsId(id);
            goodsImg.setImgUrl(img);
            goodsImgsRepository.save(goodsImg);
        }

    }

}
