package cn.wsharkcoder.marcket.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created By 方俊雄
 *
 * @date 2019/7/22 23:15
 */
@Entity
@Data
public class ActivityImg {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer activityId;
    private String imgUrl;

}
