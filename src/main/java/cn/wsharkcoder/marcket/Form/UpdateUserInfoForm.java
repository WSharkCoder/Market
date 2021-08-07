package cn.wsharkcoder.marcket.Form;

import lombok.Data;

/**
 * Created By 方俊雄
 *
 * @Date Date:2019/7/24 Time:  15:40
 */
@Data
public class UpdateUserInfoForm {
    private String password;
    private String imgUrl;
    private String address;
    private String contactInfo;
}
