package cn.wsharkcoder.marcket.Form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * Created By 方俊雄
 *
 * @date 2019/7/20 16:04
 */
@Data
public class UserFrom {
    @NotEmpty(message="用户名必填")
    private  String username;
    @NotEmpty(message = "密码必填")
    private String password;

}
