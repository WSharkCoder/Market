package cn.wsharkcoder.marcket.controller;

import cn.wsharkcoder.marcket.dataobject.Messages;
import cn.wsharkcoder.marcket.Enums.ResultEnum;
import cn.wsharkcoder.marcket.Enums.VOResultEnum;
import cn.wsharkcoder.marcket.VO.ResultVO;
import cn.wsharkcoder.marcket.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created By 方俊雄
 *
 * @Date Date:2019/7/24 Time:  17:37
 */
@RestController
@RequestMapping("/message")
@Slf4j
public class MessagesController {
    @Autowired
    private MessageService messageService;

    @GetMapping("/findall")
    public ResultVO<List<Messages>> findall(@RequestParam("goodsid") Integer id) {
        List<Messages> list = messageService.findMessage(id);
        ResultVO<List<Messages>> resultVO = new ResultVO<>();
        resultVO.setCode(VOResultEnum.MESSAGES_FINDALL_SUCCESS.getCode());
        resultVO.setMessage(VOResultEnum.MESSAGES_FINDALL_SUCCESS.getMessage());
        resultVO.setData(list);
        return resultVO;
    }
    @PostMapping("/save")
    public ResultVO<String> save(@Valid Messages messages,  BindingResult bindingResult) {
        ResultVO<String> resultVO = new ResultVO<>();
        if (bindingResult.hasErrors()) {
            log.error("【Goods Controller层】{}", ResultEnum.FORMINFO_ERROR.getMessage());
            resultVO.setMessage(VOResultEnum.MESSAGE_SAVE_FAIL.getMessage());
            resultVO.setCode(VOResultEnum.MESSAGE_SAVE_FAIL.getCode());
            resultVO.setData(ResultEnum.FORMINFO_ERROR.getMessage());
            return resultVO;
        }
      int code=  messageService.SaveMessages(messages.getGoodsId(),messages.getMessage());
        if(code==ResultEnum.MESSAGE_NULL.getCode()){
            resultVO.setMessage(VOResultEnum.MESSAGE_SAVE_FAIL.getMessage());
            resultVO.setCode(VOResultEnum.MESSAGE_SAVE_FAIL.getCode());
            resultVO.setData(ResultEnum.MESSAGE_NULL.getMessage());
            return  resultVO;
        }
        resultVO.setCode(VOResultEnum.MESSAGES_SAVE_SUCCESS.getCode());
        resultVO.setMessage(VOResultEnum.MESSAGES_SAVE_SUCCESS.getMessage());
        resultVO.setData(ResultEnum.OPERATION_SUCCESS.getMessage());
        return resultVO;
    }
    }