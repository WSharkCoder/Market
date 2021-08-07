package cn.wsharkcoder.marcket.service.impl;

import cn.wsharkcoder.marcket.dataobject.Activity;
import cn.wsharkcoder.marcket.dataobject.Participate;
import cn.wsharkcoder.marcket.dataobject.User;
import cn.wsharkcoder.marcket.repository.ActivityRepository;
import cn.wsharkcoder.marcket.repository.ParticipateRepository;
import cn.wsharkcoder.marcket.repository.UserRepository;
import cn.wsharkcoder.marcket.Enums.ResultEnum;
import cn.wsharkcoder.marcket.service.ActivityService;
import cn.wsharkcoder.marcket.utils.ConditionJdg;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created By 方俊雄
 *
 * @date 2019/7/21 20:54
 */
@Service
@Slf4j
public class ActivityServiceImpl implements ActivityService {
    @Autowired
    private ActivityRepository activityRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ParticipateRepository participateRepository;
    @Override
    public List<Activity> findAll() {
        List<Activity> activityList=activityRepository.findAll();
        return activityList;
    }
    @Override
    public int update(Activity activity) {
        if(ConditionJdg.ActivityInfoIsEmpty(activity)){
            log.error("【Activity Service层】{}", ResultEnum.PARAMETER_EMPTY.getMessage());
            return ResultEnum.PARAMETER_EMPTY.getCode();
        }

        try{
            Activity activity1=activityRepository.findById(activity.getId()).get();
            BeanUtils.copyProperties(activity,activity1);
            Activity  activity2=   activityRepository.save(activity);
            return activity2.getId();
        }catch (Exception e){
            log.error("【Activity Servier层】{}", ResultEnum.ACTIVITY_NOT_EXIST.getCode());
            return ResultEnum.ACTIVITY_NOT_EXIST.getCode();
        }

    }

    @Override
    public int detele(int activityId) {
        Activity activity=new Activity();
        try{
            activity=activityRepository.findById(activityId).get();
             activityRepository.delete(activity);
        }catch (Exception e){
            log.error("【Activity Service层】{}",ResultEnum.ACTIVITY_NOT_EXIST.getMessage());
            return ResultEnum.ACTIVITY_NOT_EXIST.getCode();
        }
        return ResultEnum.OPERATION_SUCCESS.getCode();
    }

    @Override
    public int create(Activity activity) {
        if (ConditionJdg.ActivityInfoIsEmpty(activity)) {
        log.error("【Activity Service层】{}", ResultEnum.PARAMETER_EMPTY.getMessage());
        return ResultEnum.PARAMETER_EMPTY.getCode();
        }
        Activity activity1=activityRepository.save(activity);
        return activity1.getId();
    }

    @Override
    public int join(int activityId,List<String> usernameList) {
        try{
            Activity activity=activityRepository.findById(activityId).get();
            for(String s:usernameList){
                try{
                    Participate participate=new Participate();
                    User user1=userRepository.findById(s).get();
                    participate.setUserName(user1.getUserName());
                    participate.setPromoter_name(activity.getSponsor());
                    participate.setDate(activity.getDate());
                    participate.setActivityName(activity.getName());
                    participate.setActivityAddress(activity.getAddress());
                    participate.setActivityId(activityId);
                    participateRepository.save(participate);
                }catch(Exception e){
                    log.info("【Activity Service层】{}",ResultEnum.USER_NOT_EXIST.getMessage());
                }
            }

            activity.setNumbers(activity.getNumbers()+usernameList.size());
            activityRepository.save(activity);
            return ResultEnum.OPERATION_SUCCESS.getCode();
        }catch(Exception e){
            log.error("【activity Service层】",ResultEnum.ACTIVITY_NOT_EXIST.getMessage());
            return ResultEnum.ACTIVITY_NOT_EXIST.getCode();
        }

    }
    @Override
    public   List<Activity> findBySponer(String sponer){
            List<Activity> activityList=activityRepository.findAllBySponsor(sponer);
            return activityList;
    }

    @Override
    public Activity findOne(int id) {
        try{
            Activity activity=activityRepository.findById(id).get();
            return activity;
        }catch (Exception e){
            log.error("【Activity Service层】{}",ResultEnum.ACTIVITY_NOT_EXISTED.getMessage());
        }
        return  null;

    }
}
