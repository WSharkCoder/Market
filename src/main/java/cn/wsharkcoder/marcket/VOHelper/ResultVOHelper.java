package cn.wsharkcoder.marcket.VOHelper;

import cn.wsharkcoder.marcket.Enums.VOResultEnum;
import cn.wsharkcoder.marcket.VO.ResultVO;
import lombok.Getter;

/**
 * Created By 方俊雄
 *
 * @date 2019/7/20 15:02
 */
@Getter
public class ResultVOHelper {
    //错误信息
    private ResultVO<String> resultVO=new ResultVO<>();
    public ResultVOHelper(String msg) {
       resultVO.setCode(VOResultEnum.LOGIN_FAIL.getCode());
       resultVO.setMessage(VOResultEnum.LOGIN_FAIL.getMessage());
       resultVO.setData(msg);
    }
    public ResultVOHelper(String msg,Integer code){
        resultVO.setCode(VOResultEnum.LOGON_FILE.getCode());
        resultVO.setMessage(VOResultEnum.LOGON_FILE.getMessage());
        resultVO.setData(msg);
    }


}
