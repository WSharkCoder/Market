package cn.wsharkcoder.marcket.dataobject;

import com.fasterxml.jackson.annotation.JsonIgnore;
import cn.wsharkcoder.marcket.Enums.DefaultInfoEnum;
import cn.wsharkcoder.marcket.Enums.GoodsStatusAndCategoryEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**商品表
 * Created By 方俊雄
 *
 * @date 2019/7/20 18:19
 */
@Entity
@Data
@DynamicUpdate
public class Goods {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Integer id;
    private String name;
    private BigDecimal price;
    private String detail=DefaultInfoEnum.EMPTY.getMessage();
    private String imgUrl=DefaultInfoEnum.EMPTY.getMessage();
    private String userName;
    private Integer status= GoodsStatusAndCategoryEnum.GOODS_STATUS_ONE.getCode();
    private String place;
    private String category;
    @JsonIgnore
    private Date createTime;
    @JsonIgnore
    private Date updateTime;


}
