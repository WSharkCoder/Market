package cn.wsharkcoder.marcket.Enums;

import lombok.Getter;

/**
 * Created By 方俊雄
 *
 * @date 2019/7/21 11:32
 */
@Getter
public enum GoodsStatusAndCategoryEnum {
    GOODS_STATUS_ONE(0,"出售中"),
    GOODS_STATUS_TWO(1,"已下架"),
    GOODS_STATUS_THREE(2,"已售出"),
    CATEGORY_ONE(10,"手机"),
    CATEGORY_TWO(11,"电脑"),
    CATEGORY_THREE(12,"数码"),
    CATEGORY_FOUR(13,"图书"),
    CATEGORY_FIVE(14,"服饰"),
    CATEGORY_SIX(15,"美妆"),
    CATEGORY_SEVEN(16,"家具"),
    CATEGORY_EIGHT(17,"游戏"),



    ;
    private Integer code;
    private String msg;

    GoodsStatusAndCategoryEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
