package cn.wsharkcoder.marcket.service;

import java.util.List;

/**
 * Created By 方俊雄
 *
 * @date 2019/7/22 23:23
 */
public interface ActivityImgService {
    List<String> getImgs(int id);
    void saveImgs(int id,List<String> imgs);
}
