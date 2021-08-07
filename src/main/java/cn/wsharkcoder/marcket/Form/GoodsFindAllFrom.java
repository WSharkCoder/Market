package cn.wsharkcoder.marcket.Form;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created By 方俊雄
 *
 * @date 2019/7/22 11:15
 */
@Data
public class GoodsFindAllFrom {
    private Integer id;
    private String name;
    private BigDecimal price;
    private String detail;
    private List<GoodsImgMap> imglist;
    private String userName;
    private Integer status;
    private String place;
    private String category;
    private String userimgUrl;
}
