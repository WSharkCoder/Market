package cn.wsharkcoder.marcket.controller;

import cn.wsharkcoder.marcket.Enums.ResultEnum;
import cn.wsharkcoder.marcket.Enums.VOResultEnum;
import cn.wsharkcoder.marcket.VO.ResultVO;
import cn.wsharkcoder.marcket.dataobject.Notice;
import cn.wsharkcoder.marcket.service.NoticeService;
import cn.wsharkcoder.marcket.utils.ConditionJdg;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created By 方俊雄
 *
 * @Date Date:2019/7/25 Time:  10:12
 */
@RestController
@RequestMapping("/notice")
@Slf4j
public class NoticeController {
    @Autowired
    private NoticeService noticeService;
    @PostMapping("/save")
    public ResultVO<String> save(@Valid Notice notice, BindingResult bindingResult) {
        ResultVO<String> resultVO = new ResultVO<>();
        if (bindingResult.hasErrors()|| ConditionJdg.NoticISEMPTY(notice)) {
            log.error("【Goods Controller层】{}", ResultEnum.FORMINFO_ERROR.getMessage());
            resultVO.setMessage(VOResultEnum.NOTICE_SAVE_FAIL.getMessage());
            resultVO.setCode(VOResultEnum.NOTICE_SAVE_FAIL.getCode());
            resultVO.setData(ResultEnum.FORMINFO_ERROR.getMessage());
            return resultVO;
        }
        Notice notice1 = new Notice(notice.getGoodsId(), notice.getSellerName(), notice.getBuyerName(), notice.getStatus());
        noticeService.saveNotice(notice1);
        resultVO.setCode(VOResultEnum.NOTICE_SAVE_SUCCESS.getCode());
        resultVO.setMessage(VOResultEnum.NOTICE_SAVE_SUCCESS.getMessage());
        resultVO.setData(notice1.getId() + "");
        return resultVO;
    }
    @GetMapping("/findbysellername")
    public ResultVO<List<Notice>>  findbysellername(@RequestParam("sellername")String sellername){
        List<Notice> list=noticeService.findBySellerName(sellername);
        ResultVO<List<Notice>>resultVO=new ResultVO<>();
        resultVO.setMessage(VOResultEnum.SELLER_FIND_NOTICE.getMessage());
        resultVO.setCode(VOResultEnum.SELLER_FIND_NOTICE.getCode());
        resultVO.setData(list);
        return resultVO;

    }
    @GetMapping("/findbybuyername")
    public ResultVO<List<Notice>>  findbybuyername(@RequestParam("buyername")String buyername){
        List<Notice> list=noticeService.findByBuyerName(buyername);
        ResultVO<List<Notice>>resultVO=new ResultVO<>();
        resultVO.setMessage(VOResultEnum.SELLER_FIND_NOTICE.getMessage());
        resultVO.setCode(VOResultEnum.SELLER_FIND_NOTICE.getCode());
        resultVO.setData(list);
        return resultVO;

    }
    @GetMapping("/updateStatus")
    public ResultVO<String> updateStatus(@RequestParam("id")Integer id,@RequestParam("status")Integer status)
    {
        ResultVO<String> resultVO=new ResultVO<>();
        int code=noticeService.UpdateStatus(id,status);
        if(code==ResultEnum.OPERATION_FAIL.getCode()){
            resultVO.setCode(VOResultEnum.NOTICE_UPDATE_FAIL.getCode());
            resultVO.setMessage(VOResultEnum.NOTICE_UPDATE_FAIL.getMessage());
            resultVO.setData(ResultEnum.OPERATION_FAIL.getMessage());
            return resultVO;
        }
        resultVO.setCode(VOResultEnum.NOTICE_UPDATE_SUCCESS.getCode());
        resultVO.setMessage(VOResultEnum.NOTICE_UPDATE_SUCCESS.getMessage());
        resultVO.setData(ResultEnum.OPERATION_SUCCESS.getMessage());
        return resultVO;
    }
}
