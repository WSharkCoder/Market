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
 * @date 2019/7/23 19:23
 */
@Entity
@Data
public class Shoppingcart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Integer id;
    private String username;
    private Integer goodsid;
}
