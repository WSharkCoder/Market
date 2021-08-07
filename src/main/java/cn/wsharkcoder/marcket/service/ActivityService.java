package cn.wsharkcoder.marcket.service;

import cn.wsharkcoder.marcket.dataobject.Activity;

import java.util.List;

/**
 * Created By 方俊雄
 *
 * @date 2019/7/21 19:26
 */
public interface ActivityService {
    //查询所有未过期的活动
    List<Activity> findAll();
    //更新活动
    int update(Activity activity);
    //删除活动
    int detele(int activityId);
    //增加活动
    int create(Activity activity);
    //参与活动
    int join(int activityId ,List<String> usernameList);
    //通过用户名查找用户所发起的活动
    List<Activity> findBySponer(String username);
    Activity findOne(int id);
}
