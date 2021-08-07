package cn.wsharkcoder.marcket.dataobject;

import cn.wsharkcoder.marcket.Enums.DefaultInfoEnum;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**用户表单
 * Created By 方俊雄
 *
 * @date 2019/7/20 11:06
 */
@Entity
@Data
public class User {
    @Id
    private  String userName;
    private String password;
    private String imgUrl=DefaultInfoEnum.DEFAULT_USER_IMG_URL.getMessage();
    private String address=DefaultInfoEnum.EMPTY.getMessage();
    private String contactInfo=DefaultInfoEnum.EMPTY.getMessage();
}
