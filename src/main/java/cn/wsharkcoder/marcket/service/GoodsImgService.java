package cn.wsharkcoder.marcket.service;

import java.util.List;

/**
 * Created By 方俊雄
 *
 * @date 2019/7/22 17:14
 */
public interface GoodsImgService {
    List<String> getImgs(int id);
    void saveImgs(int id,List<String> list);
}
