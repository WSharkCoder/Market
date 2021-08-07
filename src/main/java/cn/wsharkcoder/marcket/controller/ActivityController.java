package cn.wsharkcoder.marcket.controller;

import cn.wsharkcoder.marcket.Enums.ResultEnum;
import cn.wsharkcoder.marcket.Enums.VOResultEnum;
import cn.wsharkcoder.marcket.Form.ActivityFindAllFrom;
import cn.wsharkcoder.marcket.Form.ActivityImgMap;
import cn.wsharkcoder.marcket.Form.ImgFrom;
import cn.wsharkcoder.marcket.Form.JoinActivityForm;
import cn.wsharkcoder.marcket.Form.*;
import cn.wsharkcoder.marcket.VO.ResultVO;
import cn.wsharkcoder.marcket.dataobject.Activity;
import cn.wsharkcoder.marcket.service.ActivityImgService;
import cn.wsharkcoder.marcket.service.ActivityService;
import cn.wsharkcoder.marcket.service.UserService;
import cn.wsharkcoder.marcket.utils.converter.Activity2ActivityFindAllFromConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Created By 方俊雄
 *
 * @date 2019/7/22 10:29
 */
@RestController
@RequestMapping("/activity")
@Slf4j
public class ActivityController {
    @Autowired
    private ActivityService activityService;
    @Autowired
    private UserService userService;
    @Autowired
    private ActivityImgService activityImgService;

    @GetMapping("/findall")
    public ResultVO<List<ActivityFindAllFrom>> findall() {
        ResultVO<List<ActivityFindAllFrom>> listResultVO = new ResultVO<>();
        List<Activity> list = activityService.findAll();
        List<ActivityFindAllFrom> list1 = new ArrayList<>();
        for (Activity activity : list) {
            ActivityFindAllFrom activityFindAllFrom = Activity2ActivityFindAllFromConverter.converter(activity);
            List<String> list2=activityImgService.getImgs(activity.getId());
            List<ActivityImgMap> list3=new ArrayList<>();
            for(String s: list2){
                ActivityImgMap activityImgMap=new ActivityImgMap();
                activityImgMap.setImgOfActivity(s);
                list3.add(activityImgMap);
            }
            activityFindAllFrom.setImglist(list3);
            activityFindAllFrom.setContentInfo(userService.findContentInfo(activity.getSponsor()));
            String imgUrl = userService.findImgUrl(activityFindAllFrom.getSponsor());
            activityFindAllFrom.setUserimgUrl(imgUrl);
            list1.add(activityFindAllFrom);
        }
        listResultVO.setCode(VOResultEnum.ACTIVITY_FIND_SUCCESS.getCode());
        listResultVO.setMessage(VOResultEnum.ACTIVITY_FIND_SUCCESS.getMessage());
        listResultVO.setData(list1);
        return listResultVO;
    }

    @PostMapping("/create")
    public ResultVO<String> create(@Valid Activity activity, BindingResult bindingResult) {
        ResultVO<String> resultVO = new ResultVO<>();
        resultVO.setMessage(VOResultEnum.ACTIVITY_CREATE_FAIL.getMessage());
        resultVO.setCode(VOResultEnum.ACTIVITY_CREATE_FAIL.getCode());
        if (bindingResult.hasErrors()) {
            log.error("【Activity Controller层】{}", ResultEnum.FORMINFO_ERROR.getMessage());
            resultVO.setData(ResultEnum.FORMINFO_ERROR.getMessage());
            return resultVO;
        }
        int code = activityService.create(activity);
        if (code == ResultEnum.PARAMETER_EMPTY.getCode()) {
            resultVO.setData(ResultEnum.PARAMETER_EMPTY.getMessage());
            return resultVO;
        }
        resultVO.setCode(VOResultEnum.ACTIVITY_CREATE_SUCCESS.getCode());
        resultVO.setMessage(VOResultEnum.ACTIVITY_CREATE_SUCCESS.getMessage());
        resultVO.setData(code+"");
        return resultVO;
    }

    @PostMapping("/update")
    public ResultVO<String> update(@Valid Activity activity, BindingResult bindingResult) {
        ResultVO<String> resultVO = new ResultVO<>();
        resultVO.setMessage(VOResultEnum.ACTIVITY_UPDATE_FAIL.getMessage());
        resultVO.setCode(VOResultEnum.ACTIVITY_UPDATE_FAIL.getCode());
        if (bindingResult.hasErrors()) {
            log.error("【Activity Controller层】{}", ResultEnum.FORMINFO_ERROR.getMessage());
            resultVO.setData(ResultEnum.FORMINFO_ERROR.getMessage());
            return resultVO;
        }
        int code = activityService.update(activity);
        if (code == ResultEnum.PARAMETER_EMPTY.getCode()) {
            log.error("【Activity Controller层】{}", ResultEnum.PARAMETER_EMPTY.getMessage());
            resultVO.setData(ResultEnum.PARAMETER_EMPTY.getMessage());
            return resultVO;
        }
        if (code == ResultEnum.ACTIVITY_NOT_EXIST.getCode()) {
            log.error("【Activity Controller层】{}", ResultEnum.ACTIVITY_INFO_NOT_ENOUGH.getMessage());
            resultVO.setData(ResultEnum.ACTIVITY_NOT_EXIST.getMessage());
            return resultVO;
        }
        resultVO.setCode(VOResultEnum.ACTIVITY_UPDATE_SUCCESS.getCode());
        resultVO.setMessage(VOResultEnum.ACTIVITY_UPDATE_SUCCESS.getMessage());
        resultVO.setData(code+"");
        return resultVO;

    }
    @PostMapping("/upfile")
    public ResultVO<String> upfile(@Valid ImgFrom imgFrom , BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("【Goods Controller层】{}", ResultEnum.FORMINFO_ERROR.getMessage());
            ResultVO<String> resultVO = new ResultVO<>();
            resultVO.setMessage(VOResultEnum.ACTIVITY_INSERT_FILE_FAIL.getMessage());
            resultVO.setCode(VOResultEnum.ACTIVITY_INSERT_FILE_SUCCESS.getCode());
            resultVO.setData(ResultEnum.FORMINFO_ERROR.getMessage());
            return resultVO;
        }
        activityImgService.saveImgs(imgFrom.getId(),imgFrom.getList());
        ResultVO<String> resultVO=new ResultVO<>();
        resultVO.setCode(VOResultEnum.ACTIVITY_INSERT_FILE_SUCCESS.getCode());
        resultVO.setMessage(VOResultEnum.ACTIVITY_INSERT_FILE_SUCCESS.getMessage());
        resultVO.setData(ResultEnum.OPERATION_SUCCESS.getMessage());
        return resultVO;
    }
    @GetMapping("/delete")
    public ResultVO<String> delete(@RequestParam("id") int activityId) {
        int code = activityService.detele(activityId);
        ResultVO<String> resultVO = new ResultVO<>();
        if (code == ResultEnum.ACTIVITY_NOT_EXIST.getCode()) {
            log.error("【Activity Controller层】{}", ResultEnum.ACTIVITY_NOT_EXIST.getMessage());
            resultVO.setMessage(VOResultEnum.ACTIVITY_DELETE_FAIL.getMessage());
            resultVO.setCode(VOResultEnum.ACTIVITY_DELETE_FAIL.getCode());
            resultVO.setData(ResultEnum.ACTIVITY_NOT_EXIST.getMessage());
        }
        resultVO.setMessage(VOResultEnum.ACTIVITY_DELETE_SUCCESS.getMessage());
        resultVO.setCode(VOResultEnum.ACTIVITY_DELETE_SUCCESS.getCode());
        resultVO.setData(ResultEnum.OPERATION_SUCCESS.getMessage());
        return resultVO;
    }

    @GetMapping("/findbysponer")
    ResultVO<List<ActivityFindAllFrom>> findbysponer(@RequestParam("sponername") String sponername) {
        List<Activity> activityList = activityService.findBySponer(sponername);
        List<ActivityFindAllFrom> activityFindAllFromList=new ArrayList<>();
        ResultVO<List<ActivityFindAllFrom>> resultVO = new ResultVO<>();
        resultVO.setCode(VOResultEnum.ACTIVITY_FIND_BY_SPONER_SUCCESS.getCode());
        resultVO.setMessage(VOResultEnum.ACTIVITY_FIND_BY_SPONER_SUCCESS.getMessage());
        for(Activity activity:activityList){
            ActivityFindAllFrom activityFindAllFrom=new ActivityFindAllFrom();
            activityFindAllFrom=Activity2ActivityFindAllFromConverter.converter(activity);
            List<String> list=activityImgService.getImgs(activity.getId());
            List<ActivityImgMap> activityImgMapList=new ArrayList<>();
            for(String s:list){
                ActivityImgMap activityImgMap=new ActivityImgMap();
                activityImgMap.setImgOfActivity(s);
                activityImgMapList.add(activityImgMap);
            }
            activityFindAllFrom.setImglist(activityImgMapList);
            activityFindAllFrom.setContentInfo(userService.findContentInfo(activity.getSponsor()));
            String imgUrl = userService.findImgUrl(activityFindAllFrom.getSponsor());
            activityFindAllFrom.setUserimgUrl(imgUrl);
            activityFindAllFromList.add(activityFindAllFrom);
        }
        resultVO.setData(activityFindAllFromList);
        return resultVO;
    }
    @PostMapping("/join")
    ResultVO<String> join(@Valid JoinActivityForm joinActivityForm, BindingResult bindingResult) {
        ResultVO<String> resultVO = new ResultVO<>();
        resultVO.setMessage(VOResultEnum.ACTIVITY_JOIN_FAIL.getMessage());
        resultVO.setCode(VOResultEnum.ACTIVITY_JOIN_FAIL.getCode());
        if (bindingResult.hasErrors()) {
            log.error("【Activity Controller层】{}", ResultEnum.FORMINFO_ERROR.getMessage());
            resultVO.setData(ResultEnum.FORMINFO_ERROR.getMessage());
            return resultVO;
        }
        int code = activityService.join(joinActivityForm.getActivityId(), joinActivityForm.getList());
        if (code == ResultEnum.ACTIVITY_NOT_EXIST.getCode()) {
            log.error("【Activity Controller层】{}", ResultEnum.ACTIVITY_INFO_NOT_ENOUGH.getMessage());
            resultVO.setData(ResultEnum.ACTIVITY_INFO_NOT_ENOUGH.getMessage());
            return resultVO;
        }
        resultVO.setCode(VOResultEnum.ACTIVITY_JOIN_SUCCESS.getCode());
        resultVO.setMessage(VOResultEnum.ACTIVITY_JOIN_SUCCESS.getMessage());
        resultVO.setData(ResultEnum.OPERATION_SUCCESS.getMessage());
        return resultVO;
    }
}
