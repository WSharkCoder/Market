package cn.wsharkcoder.marcket.utils.converter;

import cn.wsharkcoder.marcket.dataobject.Activity;
import cn.wsharkcoder.marcket.Form.ActivityFindAllFrom;

/**
 * Created By 方俊雄
 *
 * @date 2019/7/22 13:59
 */
public class Activity2ActivityFindAllFromConverter {
    public static ActivityFindAllFrom converter(Activity activity){
        ActivityFindAllFrom activityFindAllFrom=new ActivityFindAllFrom();
        activityFindAllFrom.setId(activity.getId());
        activityFindAllFrom.setName(activity.getName());
        activityFindAllFrom.setStartTime(activity.getStartTime());
        activityFindAllFrom.setEndTime(activity.getEndTime());
        activityFindAllFrom.setDetail(activity.getDetail());
        activityFindAllFrom.setDate(activity.getDate());
        activityFindAllFrom.setAddress(activity.getAddress());
        activityFindAllFrom.setSponsor(activity.getSponsor());
        activityFindAllFrom.setNumbers(activity.getNumbers());
        activityFindAllFrom.setStatus(activity.getStatus());
    return activityFindAllFrom;
    }
}
