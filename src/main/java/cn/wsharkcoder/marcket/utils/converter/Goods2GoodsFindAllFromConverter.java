package cn.wsharkcoder.marcket.utils.converter;

import cn.wsharkcoder.marcket.dataobject.Goods;
import cn.wsharkcoder.marcket.Form.GoodsFindAllFrom;

/**
 * Created By 方俊雄
 *
 * @date 2019/7/22 11:36
 */
public class Goods2GoodsFindAllFromConverter {
    public static GoodsFindAllFrom converter(Goods goods){
        //将非图片信息转换
        GoodsFindAllFrom goodsFindAllFrom=new GoodsFindAllFrom();
        goodsFindAllFrom.setId(goods.getId());
        goodsFindAllFrom.setName(goods.getName());
        goodsFindAllFrom.setCategory(goods.getCategory());
        goodsFindAllFrom.setStatus(goods.getStatus());
        goodsFindAllFrom.setDetail(goods.getDetail());
        goodsFindAllFrom.setPlace(goods.getPlace());
        goodsFindAllFrom.setPrice(goods.getPrice());
        goodsFindAllFrom.setUserName(goods.getUserName());

        return goodsFindAllFrom;
    }

}
