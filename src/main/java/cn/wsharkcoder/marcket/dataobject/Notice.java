package cn.wsharkcoder.marcket.dataobject;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created By 方俊雄
 *
 * @Date Date:2019/7/25 Time:  9:34
 */
@Entity
@Data
public class Notice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Integer id;
    private Integer goodsId;
    private String sellerName;
    private String buyerName;
    private Integer status;

    public Notice() {
    }

    public Notice(Integer goodsId, String sellerName, String buyerName, Integer status) {
        this.goodsId = goodsId;
        this.sellerName = sellerName;
        this.buyerName = buyerName;
        this.status = status;
    }
}
