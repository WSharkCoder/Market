package cn.wsharkcoder.marcket.controller;

import cn.wsharkcoder.marcket.Form.GoodsFindAllFrom;
import cn.wsharkcoder.marcket.Form.GoodsForm;
import cn.wsharkcoder.marcket.Form.GoodsImgMap;
import cn.wsharkcoder.marcket.Form.ImgFrom;
import cn.wsharkcoder.marcket.dataobject.Goods;
import cn.wsharkcoder.marcket.service.GoodsService;
import cn.wsharkcoder.marcket.Enums.ResultEnum;
import cn.wsharkcoder.marcket.Enums.VOResultEnum;
import cn.wsharkcoder.marcket.VO.ResultVO;
import cn.wsharkcoder.marcket.service.GoodsImgService;
import cn.wsharkcoder.marcket.service.UserService;
import cn.wsharkcoder.marcket.utils.ConditionJdg;
import cn.wsharkcoder.marcket.utils.converter.Goods2GoodsFindAllFromConverter;
import cn.wsharkcoder.marcket.utils.converter.GoodsFrom2GoodsConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

/**
 * Created By 方俊雄
 *
 * @date 2019/7/21 11:46
 */
@RestController
@RequestMapping("/goods")
@Slf4j
public class GoodsController {
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private UserService userService;
    @Autowired
    private GoodsImgService goodsImgService;

    @GetMapping("/findall")
    public ResultVO<List<GoodsFindAllFrom>> find(){
        ResultVO<List<GoodsFindAllFrom>> resultVO=new ResultVO<>();
        //查询固定条数的出售商品
       List<Goods> goodsList=goodsService.findGoodsOnSale();
       List<GoodsFindAllFrom> goodsFindAllFroms=new ArrayList<>();
      for(Goods goods:goodsList){
          GoodsFindAllFrom goodsFindAllFrom= Goods2GoodsFindAllFromConverter.converter(goods);
         List<String> list=goodsImgService.getImgs(goods.getId());
         List<GoodsImgMap> list1=new ArrayList<>();
         for(String s:list){
             GoodsImgMap goodsImgMap=new GoodsImgMap();
             goodsImgMap.setImgsofgoods(s);
             list1.add(goodsImgMap);
         }
         goodsFindAllFrom.setImglist(list1);
          String imgUrl=userService.findImgUrl(goodsFindAllFrom.getUserName());
          goodsFindAllFrom.setUserimgUrl(imgUrl);
          goodsFindAllFroms.add(goodsFindAllFrom);
      }
       resultVO.setMessage(VOResultEnum.FIND_GOODS_SUCCESS.getMessage());
       resultVO.setCode(VOResultEnum.FIND_GOODS_SUCCESS.getCode());
       resultVO.setData(goodsFindAllFroms);
       return resultVO;
    }
//    @GetMapping("/findbyusername")
//    public ResultVO<List<Goods>> findbyusername(@RequestParam("username")String  username){
//        //判断username是否为空
//        if(username.isEmpty()){
//            log.error("【Goods Controller层】{}", ResultEnum.PARAMETER_EMPTY.getMessage());
//            ResultVO<List<Goods> >goodsResultVO=new ResultVO<>();
//            goodsResultVO.setCode(VOResultEnum.FIND_GOODS_FAIL.getCode());
//            goodsResultVO.setMessage(VOResultEnum.FIND_GOODS_FAIL.getMessage());
//            return goodsResultVO;
//        }
//        ResultVO<List<Goods>> resultVO=new ResultVO<>();
//        List<Goods> goodsList=goodsService.findGoodsByUserName(username);
//        resultVO.setMessage(VOResultEnum.FIND_GOODS_SUCCESS.getMessage());
//        resultVO.setCode(VOResultEnum.FIND_GOODS_SUCCESS.getCode());
//        resultVO.setData(goodsList);
//        return resultVO;
//    }
    @PostMapping("/insert")
    public ResultVO<String> insert(@Valid Goods goods, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            log.error("【Goods Controller层】{}",ResultEnum.FORMINFO_ERROR.getMessage());
           ResultVO<String> resultVO=new ResultVO<>();
           resultVO.setMessage(VOResultEnum.INSERT_GOODS_FAIL.getMessage());
           resultVO.setCode(VOResultEnum.INSERT_GOODS_FAIL.getCode());
           resultVO.setData(ResultEnum.FORMINFO_ERROR.getMessage());
           return resultVO;
        }
        //判断表单是否缺省数据
        if(ConditionJdg.GoodsInfoIsEmpty(goods)){
            log.error("【Goods Controller层】{}",ResultEnum.PARAMETER_EMPTY.getMessage());
            ResultVO<String> resultVO=new ResultVO<>();
            resultVO.setMessage(VOResultEnum.INSERT_GOODS_FAIL.getMessage());
            resultVO.setCode(VOResultEnum.INSERT_GOODS_FAIL.getCode());
            resultVO.setData(ResultEnum.PARAMETER_EMPTY.getMessage());
            return resultVO;
        }
       int id=  goodsService.InsertGoods(goods);
        ResultVO<String> resultVO=new ResultVO<>();
        resultVO.setCode(VOResultEnum.INSERT_GOODS_SUCCESS.getCode());
        resultVO.setMessage(VOResultEnum.INSERT_GOODS_SUCCESS.getMessage());
        resultVO.setData(id+"");
        return resultVO;
    }
    @PostMapping("/insertfile")
    public  ResultVO<String> insertfile(@Valid ImgFrom imgFrom, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            log.error("【Goods Controller层】{}",ResultEnum.FORMINFO_ERROR.getMessage());
            ResultVO<String> resultVO=new ResultVO<>();
            resultVO.setMessage(VOResultEnum.GOODS_INSERT_FILE_FAIL.getMessage());
            resultVO.setCode(VOResultEnum.GOODS_INSERT_FILE_SUCCESS.getCode());
            resultVO.setData(ResultEnum.FORMINFO_ERROR.getMessage());
            return resultVO;
        }
        goodsImgService.saveImgs(imgFrom.getId(), imgFrom.getList());
        ResultVO<String> resultVO=new ResultVO<>();
        resultVO.setCode(VOResultEnum.GOODS_INSERT_FILE_SUCCESS.getCode());
        resultVO.setMessage(VOResultEnum.GOODS_INSERT_FILE_SUCCESS.getMessage());
        resultVO.setData(ResultEnum.OPERATION_SUCCESS.getMessage());
        return resultVO;
    }
    @GetMapping("/findbyusername")
    ResultVO<List<GoodsFindAllFrom>> findbyusername
            (@RequestParam("username")String username){
        List<Goods> list=goodsService.findGoodsByUserName(username);
        List<GoodsFindAllFrom> list1=new ArrayList<>();
        for(Goods goods:list){
            GoodsFindAllFrom goodsFindAllFrom=Goods2GoodsFindAllFromConverter.converter(goods);
            //查找多张图片链接
            List<String> list2=goodsImgService.getImgs(goods.getId());
            List<GoodsImgMap> list3=new ArrayList<>();
            for(String s:list2){
                GoodsImgMap goodsImgMap=new GoodsImgMap();
                goodsImgMap.setImgsofgoods(s);
                list3.add(goodsImgMap);
            }
            goodsFindAllFrom.setImglist(list3);
            //查找用户头像链接
            String imgUrl=userService.findImgUrl(goodsFindAllFrom.getUserName());
            goodsFindAllFrom.setUserimgUrl(imgUrl);
            list1.add(goodsFindAllFrom);
        }
        ResultVO<List<GoodsFindAllFrom>> resultVO=new ResultVO<>();
        resultVO.setCode(VOResultEnum.GOODS_FIND_BY_USERNAME.getCode());
        resultVO.setMessage(VOResultEnum.GOODS_FIND_BY_USERNAME.getMessage());
        resultVO.setData(list1);
        return resultVO;
    }

    @GetMapping("/findbyid")
    ResultVO<GoodsFindAllFrom> findOne(@RequestParam("id")int id){
        Goods goods=goodsService.findOne(id);
        GoodsFindAllFrom goodsFindAllFrom=new GoodsFindAllFrom();
        ResultVO<GoodsFindAllFrom> goodsResultVO=new ResultVO<>();
        if(goods==null){
            goodsResultVO.setMessage(VOResultEnum.FIND_GOODSBYID_FAIL.getMessage());
            goodsResultVO.setCode(VOResultEnum.FIND_GOODSBYID_FAIL.getCode());
            goodsResultVO.setData(goodsFindAllFrom);
            return goodsResultVO;
        }
        goodsResultVO.setCode(VOResultEnum.FIND_GOODS_SUCCESS.getCode());
        goodsResultVO.setMessage(VOResultEnum.FIND_GOODS_SUCCESS.getMessage());
        goodsFindAllFrom=Goods2GoodsFindAllFromConverter.converter(goods);
        List<String> list=goodsImgService.getImgs(goods.getId());
        List<GoodsImgMap> list1=new ArrayList<>();
        for(String s: list){
            GoodsImgMap goodsImgMap=new GoodsImgMap();
            goodsImgMap.setImgsofgoods(s);
            list1.add(goodsImgMap);
        }

        goodsFindAllFrom.setImglist(list1);
        String imgUrl=userService.findImgUrl(goodsFindAllFrom.getUserName());
        goodsFindAllFrom.setUserimgUrl(imgUrl);
        goodsResultVO.setData(goodsFindAllFrom);
        return goodsResultVO;
    }
    @PostMapping("/update")
    ResultVO<String>  update(@Valid GoodsForm goodsForm, BindingResult bindingResult){
        ResultVO<String> resultVO=new ResultVO<>();
        if(bindingResult.hasErrors()){
            log.error("【Goods Controller层】{}",ResultEnum.FORMINFO_ERROR.getMessage());
            resultVO.setMessage(VOResultEnum.UPDATA_GOODS_FAIL.getMessage());
            resultVO.setCode(VOResultEnum.UPDATA_GOODS_FAIL.getCode());
            resultVO.setData(ResultEnum.FORMINFO_ERROR.getMessage());
        }
        Goods goods=new Goods();
        try{
            goods=GoodsFrom2GoodsConverter.converter(goodsForm);
            int result= goodsService.updateGoodsInfo(goods);
          resultVO.setMessage(VOResultEnum.UPDATE_GOODS_SUCCESS.getMessage());
          resultVO.setCode(VOResultEnum.UPDATE_GOODS_SUCCESS.getCode());
          resultVO.setData(""+result);
          return  resultVO;
        }catch(Exception e){
            log.error("【Goods controller层】{}",ResultEnum.PARAMETER_EMPTY.getMessage());
        }
        resultVO.setMessage(VOResultEnum.UPDATA_GOODS_FAIL.getMessage());
        resultVO.setCode(VOResultEnum.UPDATA_GOODS_FAIL.getCode());
        resultVO.setData(ResultEnum.BEANUTIL_ERROR.getMessage());
        return resultVO;
    }
    @GetMapping("/delete")
    public  ResultVO<String> delete(@RequestParam("id")int id){
        int code =goodsService.deleteGoods(id );
        ResultVO<String> resultVO=new ResultVO<>();
        if(code==ResultEnum.GOOD_NOT_EXISTED.getCode()){
            log.error("【Goods Controller层】{}",ResultEnum.GOOD_NOT_EXISTED.getMessage());
            resultVO.setCode(VOResultEnum.DELETE_GOODS_FAIL.getCode());
            resultVO.setMessage(VOResultEnum.DELETE_GOODS_FAIL.getMessage());
            resultVO.setData(ResultEnum.GOOD_NOT_EXISTED.getMessage());
            return resultVO;
        }
        resultVO.setCode(VOResultEnum.DELETE_GOODS_SUCCESS.getCode());
        resultVO.setMessage(VOResultEnum.DELETE_GOODS_SUCCESS.getMessage());
        resultVO.setData(ResultEnum.OPERATION_SUCCESS.getMessage());
        return resultVO;
    }
    @GetMapping("/updatestatus")
    public  ResultVO<String>  updatestatus(@RequestParam("id") int id,@RequestParam("status")int status){
        int code=goodsService.updateGoodsStatus(id,status);
        ResultVO<String> resultVO=new ResultVO<>();
        if(ResultEnum.GOOD_NOT_EXISTED.getCode()==code){
            log.error("【Goods Controller层】{}",ResultEnum.GOOD_NOT_EXISTED.getMessage());
            resultVO.setCode(VOResultEnum.STATUS_CHANGE_FAIL.getCode());
            resultVO.setMessage(VOResultEnum.STATUS_CHANGE_FAIL.getMessage());
            resultVO.setData(ResultEnum.GOOD_NOT_EXISTED.getMessage());
            return resultVO;
        }
        resultVO.setCode(VOResultEnum.STATUS_CHANGE_SUCCESS.getCode());
        resultVO.setMessage(VOResultEnum.STATUS_CHANGE_SUCCESS.getMessage());
        resultVO.setData(ResultEnum.OPERATION_SUCCESS.getMessage());
        return resultVO;

    }
    /**
     * 按类目进行查询
     */
    @GetMapping("/findallbycategory")
     public ResultVO<List<GoodsFindAllFrom>> findallbycategory(@RequestParam("category") String catsgory){
        ResultVO<List<GoodsFindAllFrom>> resultVO=new ResultVO<>();
        resultVO.setCode(VOResultEnum.GOODS_FINDALL_BY_CATEGORY_SUCCESS.getCode());
        resultVO.setMessage(VOResultEnum.GOODS_FINDALL_BY_CATEGORY_SUCCESS.getMessage());
       List<Goods> goodsList=goodsService.findByCategory(catsgory);
       List<GoodsFindAllFrom> goodsFindAllFroms=new ArrayList<>();
       for(Goods goods: goodsList){
           GoodsFindAllFrom goodsFindAllFrom=Goods2GoodsFindAllFromConverter.converter(goods);
           //查找多张图片链接
           List<String> list2=goodsImgService.getImgs(goods.getId());
           List<GoodsImgMap> list3=new ArrayList<>();
           for(String s:list2){
               GoodsImgMap goodsImgMap=new GoodsImgMap();
               goodsImgMap.setImgsofgoods(s);
               list3.add(goodsImgMap);
           }
           goodsFindAllFrom.setImglist(list3);
           //查找用户头像链接
           String imgUrl=userService.findImgUrl(goodsFindAllFrom.getUserName());
           goodsFindAllFrom.setUserimgUrl(imgUrl);
           goodsFindAllFroms.add(goodsFindAllFrom);
       }
       resultVO.setData(goodsFindAllFroms);
        return resultVO;
    }
}
