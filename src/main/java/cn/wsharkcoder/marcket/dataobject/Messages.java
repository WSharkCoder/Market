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
 * @Date Date:2019/7/24 Time:  17:03
 */
@Entity
@Data
public class Messages {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Integer id;
    private Integer goodsId;
    private String message;

    public Messages(Integer goodsId, String message) {
        this.goodsId = goodsId;
        this.message = message;
    }

    public Messages() {
    }
}
