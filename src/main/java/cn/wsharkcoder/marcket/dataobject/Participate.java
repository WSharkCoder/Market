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
 * @date 2019/7/20 22:37
 */
@Entity
@Data
public class Participate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Integer id;
    private String userName;
    private String promoter_name;
    private String date;
    private String activityName;
    private String activityAddress;
    private Integer activityId;

}
