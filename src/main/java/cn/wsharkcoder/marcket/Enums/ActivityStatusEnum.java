package cn.wsharkcoder.marcket.Enums;

/**
 * Created By 方俊雄
 *
 * @date 2019/7/22 9:14
 */
public enum ActivityStatusEnum {
    NOT_STARTED(0,"未开始"),
    GOING_ON(1,"进行中"),
    END(2,"")
    ;
    private Integer code;
    private String message;

    ActivityStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
