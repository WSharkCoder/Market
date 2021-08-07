package cn.wsharkcoder.marcket.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created By 方俊雄
 *
 * @date 2019/7/24 9:31
 */
@Entity
@Data
public class Collections {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String  username;
    private Integer goodsId;
    private Integer activityId;

    public Collections(String username, Integer goodsId, Integer activityId) {
        this.username = username;
        this.goodsId = goodsId;
        this.activityId = activityId;
    }
    public Collections(){

    }
}
