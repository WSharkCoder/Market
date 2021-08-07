package cn.wsharkcoder.marcket.Form;

import cn.wsharkcoder.marcket.Enums.DefaultInfoEnum;
import cn.wsharkcoder.marcket.Enums.GoodsStatusAndCategoryEnum;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Created By 方俊雄
 *
 * @date 2019/7/21 15:12
 */
@Data
public class GoodsForm {
    private Integer id;
    private String name;
    private BigDecimal price;
    private String detail= DefaultInfoEnum.EMPTY.getMessage();
    private String imgurl=DefaultInfoEnum.EMPTY.getMessage();
    private String username;
    private Integer status= GoodsStatusAndCategoryEnum.GOODS_STATUS_ONE.getCode();
    private String place;
    private String category;
}
