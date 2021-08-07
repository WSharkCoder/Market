package cn.wsharkcoder.marcket.controller;

import cn.wsharkcoder.marcket.Form.*;
import cn.wsharkcoder.marcket.dataobject.Activity;
import cn.wsharkcoder.marcket.dataobject.Goods;
import cn.wsharkcoder.marcket.dataobject.Participate;
import cn.wsharkcoder.marcket.dataobject.User;
import cn.wsharkcoder.marcket.service.*;
import cn.wsharkcoder.marcket.Enums.ResultEnum;
import cn.wsharkcoder.marcket.Enums.VOResultEnum;
import cn.wsharkcoder.marcket.utils.converter.Activity2ActivityFindAllFromConverter;
import cn.wsharkcoder.marcket.utils.converter.Goods2GoodsFindAllFromConverter;
import cn.wsharkcoder.marcket.utils.converter.UserFrom2UserConverter;
import cn.wsharkcoder.marcket.*;
import cn.wsharkcoder.marcket.VO.CollectionsResultVO;
import cn.wsharkcoder.marcket.VO.ResultVO;
import cn.wsharkcoder.marcket.VOHelper.ResultVOHelper;

import cn.wsharkcoder.marcket.utils.ConditionJdg;

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
 * @date 2019/7/20 13:42
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private GoodsImgService goodsImgService;
    @Autowired
    private CollectionsService collectionsService;
    @Autowired
    private ActivityImgService activityImgService;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private ActivityService activityService;

    private List<ActivityFindAllFrom> ActivityConverter(List<Activity> activityList) {
        List<ActivityFindAllFrom> activityFindAllFromList = new ArrayList<>();
        for (Activity activity : activityList) {
            ActivityFindAllFrom activityFindAllFrom = Activity2ActivityFindAllFromConverter.converter(activity);
            List<String> list2 = activityImgService.getImgs(activity.getId());
            List<ActivityImgMap> list3 = new ArrayList<>();
            for (String s : list2) {
                ActivityImgMap activityImgMap = new ActivityImgMap();
                activityImgMap.setImgOfActivity(s);
                list3.add(activityImgMap);
            }
            activityFindAllFrom.setImglist(list3);
            activityFindAllFrom.setContentInfo(userService.findContentInfo(activity.getSponsor()));
            String imgUrl = userService.findImgUrl(activityFindAllFrom.getSponsor());
            activityFindAllFrom.setUserimgUrl(imgUrl);
            activityFindAllFromList.add(activityFindAllFrom);
        }
        return activityFindAllFromList;
    }

    private List<GoodsFindAllFrom> GoodsConverter(List<Goods> goodsList) {
        List<GoodsFindAllFrom> list1 = new ArrayList<>();
        for (Goods goods : goodsList) {
            GoodsFindAllFrom goodsFindAllFrom = Goods2GoodsFindAllFromConverter.converter(goods);
            //查找多张图片链接
            List<String> list2 = goodsImgService.getImgs(goods.getId());
            List<GoodsImgMap> list3 = new ArrayList<>();
            for (String s : list2) {
                GoodsImgMap goodsImgMap = new GoodsImgMap();
                goodsImgMap.setImgsofgoods(s);
                list3.add(goodsImgMap);
            }
            goodsFindAllFrom.setImglist(list3);
            //查找用户头像链接
            String imgUrl = userService.findImgUrl(goodsFindAllFrom.getUserName());
            goodsFindAllFrom.setUserimgUrl(imgUrl);
            list1.add(goodsFindAllFrom);
        }
        return list1;
    }

    @PostMapping("/login")
    public ResultVO<String> login(@RequestParam("username") String username,
                                  @RequestParam("password") String password) {
        int code = userService.checkPsd(username, password);
        if (code == ResultEnum.PARAMETER_EMPTY.getCode()) {
            return new ResultVOHelper(ResultEnum.PARAMETER_EMPTY.getMessage()).getResultVO();
        }
        if (code == ResultEnum.USER_NOT_EXIST.getCode()) {
            return new ResultVOHelper(ResultEnum.USER_NOT_EXIST.getMessage()).getResultVO();
        }
        if (code == ResultEnum.PASSWORD_ERROR.getCode()) {
            return new ResultVOHelper(ResultEnum.PASSWORD_ERROR.getMessage()).getResultVO();
        }
        ResultVO<String> resultVO = new ResultVO<>();
        resultVO.setCode(VOResultEnum.LOGIN_SUCCESS.getCode());
        resultVO.setMessage(VOResultEnum.LOGIN_SUCCESS.getMessage());
        resultVO.setData(ResultEnum.OPERATION_SUCCESS.getMessage());
        return resultVO;
    }

    @PostMapping("/logon")
    public ResultVO<String> register(@Valid UserFrom userFrom, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("【user Controller层】{}", ResultEnum.FORMINFO_ERROR.getMessage());
            return new ResultVOHelper(ResultEnum.FORMINFO_ERROR.getMessage(), 1).getResultVO();
        }

        User user = UserFrom2UserConverter.convert(userFrom);
        Integer code = userService.registerUser(user);
        if (code == ResultEnum.USERINFO_INCOMPLETE.getCode()) {
            return new ResultVOHelper(ResultEnum.USERINFO_INCOMPLETE.getMessage(), 1).getResultVO();
        }
        if (code == ResultEnum.USER_IS_EXISTED.getCode()) {
            return new ResultVOHelper(ResultEnum.USER_IS_EXISTED.getMessage(), 1).getResultVO();
        }
        ResultVO<String> resultVO = new ResultVO<>();
        resultVO.setCode(VOResultEnum.LOGON_SUCCESS.getCode());
        resultVO.setMessage(VOResultEnum.LOGIN_SUCCESS.getMessage());
        resultVO.setData(ResultEnum.USERINFO_IN_DATA.getMessage());
        return resultVO;
    }

    @GetMapping("/participate")
    public ResultVO<List<Participate>> participate(@RequestParam("username") String username) {
        ResultVO<List<Participate>> resultVO = new ResultVO<>();
        List<Participate> participateList = userService.findActivity(username);
        resultVO.setCode(VOResultEnum.USER_FIND_ACTIVITY.getCode());
        resultVO.setMessage(VOResultEnum.USER_FIND_ACTIVITY.getMessage());
        resultVO.setData(participateList);
        return resultVO;
    }

    @GetMapping("/addshoppingcart")
    public ResultVO<String> addshoppingcart(@RequestParam("username") String username, @RequestParam("goodsid") int id) {
        int code = userService.CollectGoods(id, username);
        ResultVO<String> resultVO = new ResultVO<>();
        if (code == ResultEnum.GOODS_COLLECTED.getCode()) {
            log.info("【User Controller层】{}", ResultEnum.Goods_IS_EXISTED.getMessage());
            resultVO.setCode(VOResultEnum.GOODS_COLLECTED.getCode());
            resultVO.setMessage(VOResultEnum.GOODS_COLLECTED.getMessage());
            resultVO.setData(ResultEnum.GOODS_COLLECTED.getMessage());
            return resultVO;
        }
        resultVO.setCode(VOResultEnum.GOODS_COLLECTED_SUCCESS.getCode());
        resultVO.setMessage(VOResultEnum.GOODS_COLLECTED_SUCCESS.getMessage());
        resultVO.setData(ResultEnum.OPERATION_SUCCESS.getMessage());
        return resultVO;

    }

    @GetMapping("/deleteshoppingcart")
    public ResultVO<String> deleteshoppingcart(@RequestParam("username") String username, @RequestParam("goodsid") int id) {
        int code = userService.deleteCollectGoods(id, username);
        ResultVO<String> resultVO = new ResultVO<>();
        if (code == ResultEnum.GOODSCOLLERCTION_NOT_EXIST.getCode()) {
            resultVO.setCode(VOResultEnum.GOODS_DELETE_COLLECTS_FAIL.getCode());
            resultVO.setMessage(VOResultEnum.GOODS_DELETE_COLLECTS_FAIL.getMessage());
            resultVO.setData(ResultEnum.GOODSCOLLERCTION_NOT_EXIST.getMessage());
            return resultVO;
        }
        resultVO.setMessage(VOResultEnum.GOODS_DELETE_COLLECTS_SUCCESS.getMessage());
        resultVO.setCode(VOResultEnum.GOODS_DELETE_COLLECTS_SUCCESS.getCode());
        resultVO.setData(ResultEnum.OPERATION_SUCCESS.getMessage());
        return resultVO;
    }

    @GetMapping("/findinshoppingcart")
    public ResultVO<List<GoodsFindAllFrom>> findinshoppingcart(@RequestParam("username") String username) {
        List<GoodsFindAllFrom> goodsFindAllFroms = new ArrayList<>();
        List<Goods> goodsList = userService.showCollections(username);
        goodsFindAllFroms = GoodsConverter(goodsList);
        ResultVO<List<GoodsFindAllFrom>> resultVO = new ResultVO<>();
        resultVO.setCode(VOResultEnum.COLLECTIONS_SHOW_SUCCESS.getCode());
        resultVO.setMessage(VOResultEnum.COLLECTIONS_SHOW_SUCCESS.getMessage());
        resultVO.setData(goodsFindAllFroms);
        return resultVO;
    }

    @GetMapping("/showcollections")
    public CollectionsResultVO showcollections(@RequestParam("username") String username) {
        //查找商品信息并封装
        List<Activity> activityList = collectionsService.findCollectActivityByUserName(username);
        List<ActivityFindAllFrom> activityFindAllFromList = ActivityConverter(activityList);
        //查找活动信息并封装
        List<Goods> goodsList = collectionsService.findCollectGoodsByUserName(username);
        List<GoodsFindAllFrom> goodsFindAllFromList = GoodsConverter(goodsList);
        //封装数据
        CollectionsResultVO collectionsResultVO = new CollectionsResultVO();
        collectionsResultVO.setActivityList(activityFindAllFromList);
        collectionsResultVO.setGoodsList(goodsFindAllFromList);
        collectionsResultVO.setCode(VOResultEnum.COLLECTIOSN_SHOW_SUCCESS.getCode());
        collectionsResultVO.setMessage(VOResultEnum.COLLECTIOSN_SHOW_SUCCESS.getMessage());
        return collectionsResultVO;
    }

    @GetMapping("/savegoodscollections")
    public ResultVO<String> savegoodscollections(@RequestParam("username") String username, @RequestParam("id") Integer id) {
        Goods goods = goodsService.findOne(id);
        ResultVO<String> resultVO = new ResultVO<>();
        resultVO.setCode(VOResultEnum.COLLECTIONS_SAVE_SUCCESS.getCode());
        resultVO.setMessage(VOResultEnum.COLLECTIONS_SAVE_SUCCESS.getMessage());
        resultVO.setData(ResultEnum.OPERATION_SUCCESS.getMessage());
        if (goods != null) {
            int code = collectionsService.saveGoodsCollections(id, username);
            if (code == ResultEnum.GOODS_COLLECTED_TWICE.getCode()) {
                resultVO.setCode(VOResultEnum.COLLECTIONS_SAVE_TWICE.getCode());
                resultVO.setMessage(VOResultEnum.COLLECTIONS_SAVE_TWICE.getMessage());
                resultVO.setData(ResultEnum.GOODS_COLLECTED_TWICE.getMessage());
                return resultVO;
            }
            return resultVO;
        }
        resultVO.setCode(VOResultEnum.COLLECTION_SAXE_FAIL.getCode());
        resultVO.setMessage(VOResultEnum.COLLECTION_SAXE_FAIL.getMessage());
        resultVO.setData(ResultEnum.COLLECTIONS_FAIL.getMessage());
        return resultVO;
    }

    @GetMapping("/saveactivitycollections")
    public ResultVO<String> saveactivitycollections(@RequestParam("username") String username, @RequestParam("id") Integer id) {
        ResultVO<String> resultVO = new ResultVO<>();
        resultVO.setCode(VOResultEnum.COLLECTIONS_SAVE_SUCCESS.getCode());
        resultVO.setMessage(VOResultEnum.COLLECTIONS_SAVE_SUCCESS.getMessage());
        resultVO.setData(ResultEnum.OPERATION_SUCCESS.getMessage());
        Activity activity = activityService.findOne(id);
        if (activity != null) {
            int code = collectionsService.saveActivityCollections(id, username);
            if (code == ResultEnum.ACTIVITY_COLLECTED_TWICE.getCode()) {
                resultVO.setCode(VOResultEnum.COLLECTIONS_SAVE_TWICE.getCode());
                resultVO.setMessage(VOResultEnum.COLLECTIONS_SAVE_TWICE.getMessage());
                resultVO.setData(ResultEnum.ACTIVITY_COLLECTED_TWICE.getMessage());
                return resultVO;
            }
            return resultVO;
        }
        resultVO.setCode(VOResultEnum.COLLECTION_SAXE_FAIL.getCode());
        resultVO.setMessage(VOResultEnum.COLLECTION_SAXE_FAIL.getMessage());
        resultVO.setData(ResultEnum.COLLECTIONS_FAIL.getMessage());
        return resultVO;
    }

    @GetMapping("/deletegoodscollections")
    public ResultVO<String> deletegoodscollections(@RequestParam("username") String username, @RequestParam("id") Integer id) {
        int code = collectionsService.deleteGoodsCollections(id, username);
        ResultVO<String> resultVO = new ResultVO<>();
        if (code == ResultEnum.COLLECTIONS_DELETE_FAIL.getCode()) {
            resultVO.setCode(VOResultEnum.COLLECTIONS_DELETE_FAIL.getCode());
            resultVO.setMessage(VOResultEnum.COLLECTIONS_DELETE_FAIL.getMessage());
            resultVO.setData(ResultEnum.COLLECTIONS_DELETE_FAIL.getMessage());
            return resultVO;
        }
        resultVO.setCode(VOResultEnum.COLLECTIONS_DELETE_SUCCESS.getCode());
        resultVO.setMessage(VOResultEnum.COLLECTIONS_DELETE_SUCCESS.getMessage());
        resultVO.setData(ResultEnum.OPERATION_SUCCESS.getMessage());
        return resultVO;
    }

    @GetMapping("/deleteactivitycollections")
    public ResultVO<String> deleteactivitycollections(@RequestParam("username") String username, @RequestParam("id") Integer id) {
        int code = collectionsService.deleteActivityCollections(id, username);
        ResultVO<String> resultVO = new ResultVO<>();
        if (code == ResultEnum.COLLECTIONS_DELETE_FAIL.getCode()) {
            resultVO.setCode(VOResultEnum.COLLECTIONS_DELETE_FAIL.getCode());
            resultVO.setMessage(VOResultEnum.COLLECTIONS_DELETE_FAIL.getMessage());
            resultVO.setData(ResultEnum.COLLECTIONS_DELETE_FAIL.getMessage());
            return resultVO;
        }
        resultVO.setCode(VOResultEnum.COLLECTIONS_DELETE_SUCCESS.getCode());
        resultVO.setMessage(VOResultEnum.COLLECTIONS_DELETE_SUCCESS.getMessage());
        resultVO.setData(ResultEnum.OPERATION_SUCCESS.getMessage());
        return resultVO;
    }

    @GetMapping("/getuserinfo")
    public ResultVO<User> getuserinfo(@RequestParam("username") String username) {
        User user = userService.findOne(username);
        ResultVO<User> userResultVO = new ResultVO<>();
        userResultVO.setData(user);
        if (user == null) {
            userResultVO.setCode(VOResultEnum.USER_NOT_EXIST.getCode());
            userResultVO.setMessage(VOResultEnum.USER_NOT_EXIST.getMessage());
        }
        userResultVO.setCode(VOResultEnum.USER_FIND_INFO.getCode());
        userResultVO.setMessage(VOResultEnum.USER_FIND_INFO.getMessage());
        return userResultVO;
    }

    @PostMapping("/updateuserinfo")
    public ResultVO<String> updateuserinfo(@Valid User user, BindingResult bindingResult) {
        ResultVO<String> resultVO = new ResultVO<>();
        if (bindingResult.hasErrors()) {
            log.error("【Goods Controller层】{}", ResultEnum.FORMINFO_ERROR.getMessage());
            resultVO.setMessage(VOResultEnum.USER_INFO_UPDATE_FAIL.getMessage());
            resultVO.setCode(VOResultEnum.USER_INFO_UPDATE_FAIL.getCode());
            resultVO.setData(ResultEnum.FORMINFO_ERROR.getMessage());
            return resultVO;
        }
        if (ConditionJdg.UpdateUserInfoFormISEnough(user)) {
            log.error("【Goods Controller层】{}", ResultEnum.FORMINFO_ERROR.getMessage());
            resultVO.setMessage(VOResultEnum.USER_INFO_UPDATE_FAIL.getMessage());
            resultVO.setCode(VOResultEnum.USER_INFO_UPDATE_FAIL.getCode());
            resultVO.setData(ResultEnum.FORMINFO_ERROR.getMessage());
            return resultVO;
        }
        int code = userService.updateUserInfo(user);
        if (code == ResultEnum.USER_NOT_EXIST.getCode()) {
            resultVO.setCode(VOResultEnum.USER_INFO_NOT_EXIST.getCode());
            resultVO.setMessage(VOResultEnum.USER_INFO_NOT_EXIST.getMessage());
            resultVO.setData(ResultEnum.USER_NOT_EXIST.getMessage());
            return resultVO;
        }
        resultVO.setCode(VOResultEnum.USER_INFO_UPDATE_SUCCESS.getCode());
        resultVO.setMessage(VOResultEnum.USER_INFO_UPDATE_SUCCESS.getMessage());
        resultVO.setData(ResultEnum.OPERATION_SUCCESS.getMessage());

        return resultVO;
    }

}
