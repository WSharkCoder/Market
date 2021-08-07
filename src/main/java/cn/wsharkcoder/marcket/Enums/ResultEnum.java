package cn.wsharkcoder.marcket.Enums;

import lombok.Getter;

/**
 * Created By 方俊雄
 *
 * @date 2019/7/20 12:31
 */
@Getter
public enum ResultEnum {
    OPERATION_SUCCESS(0,"操作成功"),
    PARAMETER_EMPTY(1,"部分或全部参数为空"),
    USER_NOT_EXIST(2,"用户不存在"),
    PASSWORD_ERROR(3,"密码错误"),
    USERINFO_INCOMPLETE(4,"用户信息残缺"),
    USERINFO_IN_DATA(5,"信息入库成功"),
    FORMINFO_ERROR(6,"表单信息有误"),
    USER_IS_EXISTED(7,"用户名已存在 "),
    Goods_IS_EXISTED(8,"商品同名"),
    GOOD_NOT_EXISTED(9,"商品不存在"),
    BEANUTIL_ERROR(10,"BeanUtils Copy方法异常"),
    ACTIVITY_NOT_EXIST(11,"活动Id不在库"),
    ACTIVITY_INFO_NOT_ENOUGH(12,"activity表单信息不足"),
    GOODS_COLLECTED(13,"商品被重复加入购物车"),
    GOODSCOLLERCTION_NOT_EXIST(14,"商品已经不在购物车"),
    COLLECTIONS_GOOD_NOT_EXIST(15,"收藏夹内商品信息已不在库"),
    COLLECTIONS_ACTIVITY_NOT_EXIST(16,"收藏夹活动信息不在库"),
    ACTIVITY_NOT_EXISTED(17,"活动不存在"),
    COLLECTIONS_FAIL(18,"收藏id不在库"),
    GOODS_COLLECTED_TWICE(19,"商品重复收藏"),
    ACTIVITY_COLLECTED_TWICE(20,"活动重复收藏"),
    COLLECTIONS_DELETE_SUCCESS(21,"删除收藏商品成功"),
    COLLECTIONS_DELETE_FAIL(22,"商品/活动删除失败,商品/活动不在库中"),
    MESSAGE_NULL(23,"留言录入失败，留言为空"),
    OPERATION_FAIL(24,"操作失败"),


    ;
    private Integer code;
    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
