package cn.wsharkcoder.marcket.utils.converter;

import cn.wsharkcoder.marcket.dataobject.Goods;
import cn.wsharkcoder.marcket.Form.GoodsForm;

/**
 * Created By 方俊雄
 *
 * @date 2019/7/21 17:05
 */
public class GoodsFrom2GoodsConverter {
    public static Goods converter(GoodsForm goodsForm){
        Goods goods=new Goods();
        goods.setId(goodsForm.getId());
        goods.setName(goodsForm.getName());
        goods.setPrice(goodsForm.getPrice());
        goods.setImgUrl(goodsForm.getImgurl());
        goods.setUserName(goodsForm.getUsername());
        goods.setStatus(goodsForm.getStatus());
        goods.setPlace(goodsForm.getPlace());
        goods.setDetail(goodsForm.getDetail());
        goods.setCategory(goodsForm.getCategory());
        return goods;
    }

}
