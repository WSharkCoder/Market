package cn.wsharkcoder.marcket.dataobject;

import com.fasterxml.jackson.annotation.JsonIgnore;
import cn.wsharkcoder.marcket.Enums.DefaultInfoEnum;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


/**
 * Created By 方俊雄
 *
 * @date 2019/7/20 20:24
 */
@Entity
@Data
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Integer id;
    private String name;
    private String startTime;
    private String endTime;
    /**
     * 详情可为空
     */
    private String detail= DefaultInfoEnum.EMPTY.getMessage();
    private String date;
    private String address;
    private String sponsor;
    private Integer numbers;
    private Integer status;

}
