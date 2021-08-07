package cn.wsharkcoder.marcket.VO;

import cn.wsharkcoder.marcket.Form.ActivityFindAllFrom;
import cn.wsharkcoder.marcket.Form.GoodsFindAllFrom;
import lombok.Data;

import java.util.List;

/**
 * Created By 方俊雄
 *
 * @date 2019/7/24 10:27
 */
@Data
public class CollectionsResultVO {
    private Integer code;
    private String message;
    private List<ActivityFindAllFrom> activityList;
    private List<GoodsFindAllFrom> goodsList;
}
