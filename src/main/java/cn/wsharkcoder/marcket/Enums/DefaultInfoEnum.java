package cn.wsharkcoder.marcket.Enums;

import lombok.Getter;

/**
 * Created By 方俊雄
 *
 * @date 2019/7/20 11:36
 */
@Getter
public enum DefaultInfoEnum {
    EMPTY(0,""),
    DEFAULT_USER_IMG_URL(1,"http://129.211.66.191/e6d4f696-d373-4ad6-9879-03622349cdda.jpg"),
    ;
    private Integer code;
    private String message;

    DefaultInfoEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
