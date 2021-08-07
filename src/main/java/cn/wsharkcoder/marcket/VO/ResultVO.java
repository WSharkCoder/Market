package cn.wsharkcoder.marcket.VO;

import lombok.Data;

/**返回信息
 * Created By 方俊雄
 *
 * @date 2019/7/20 13:50
 */
@Data
public class ResultVO<T> {
    private Integer code;
    private String message;
    private T data;

}
