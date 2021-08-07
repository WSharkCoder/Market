package cn.wsharkcoder.marcket.Enums;

import lombok.Getter;

/**
 * Created By 方俊雄
 *
 * @date 2019/7/20 14:27
 */
@Getter
public enum VOResultEnum {
    LOGIN_SUCCESS(0,"登陆成功"),
    LOGIN_FAIL(1,"登陆失败"),
   LOGON_FILE(2,"注册失败"),
    LOGON_SUCCESS(3,"注册成功"),
    FIND_GOODS_SUCCESS(4,"商品查找成功"),
    FIND_GOODS_FAIL(5,"商品查找失败"),
    INSERT_GOODS_FAIL(6,"商品添加失败"),
    INSERT_GOODS_SUCCESS(7,"商品添加成功"),
    FIND_GOODSBYID_FAIL(8,"id对应商品查询不到"),
    UPDATA_GOODS_FAIL(9,"更新商品失败"),
    UPDATE_GOODS_SUCCESS(10,"更新商品成功"),
    DELETE_GOODS_FAIL(11,"商品信息删除失败"),
    DELETE_GOODS_SUCCESS(12,"商品信息删除成功"),
    STATUS_CHANGE_FAIL(13,"商品状态更新失败"),
    STATUS_CHANGE_SUCCESS(14,"商品状态更新成功"),
    ACTIVITY_FIND_SUCCESS(15,"活动查询成功"),
    ACTIVITY_CREATE_FAIL(16,"活动发布失败"),
    ACTIVITY_CREATE_SUCCESS(17,"活动发布成功"),
    ACTIVITY_UPDATE_FAIL(18,"活动更新失败"),
    ACTIVITY_UPDATE_SUCCESS(19,"活动更新成功"),
    ACTIVITY_DELETE_FAIL(20,"活动删除失败"),
    ACTIVITY_DELETE_SUCCESS(21,"活动删除成功"),
   ACTIVITY_FIND_BY_SPONER_SUCCESS(22,"查询成功"),
   ACTIVITY_JOIN_FAIL(23,"加入活动失败"),
  ACTIVITY_JOIN_SUCCESS(24,"成功加入活动"),
    GOODS_INSERT_FILE_FAIL(25,"商品图片入库失败"),
    GOODS_INSERT_FILE_SUCCESS(26,"商品图片入库成功"),
    GOODS_FIND_BY_USERNAME(27,"查找用户出售商品成功"),
    ACTIVITY_INSERT_FILE_FAIL(28,"活动图片入库失败"),
    ACTIVITY_INSERT_FILE_SUCCESS(29,"活动图片入库成功"),
    USER_FIND_ACTIVITY(30,"参与活动查询成功"),
    GOODS_FINDALL_BY_CATEGORY_SUCCESS(31,"类目查询成功"),
    GOODS_COLLECTED(32,"商品加入购物车失败，商品重复收藏"),
    GOODS_COLLECTED_SUCCESS(33,"商品加入购物车成功"),
    GOODS_DELETE_COLLECTS_FAIL(34,"删除购物车商品失败，商品已经不在收藏夹内"),
    GOODS_DELETE_COLLECTS_SUCCESS(35,"商品已从购物车删除"),
    COLLECTIONS_SHOW_SUCCESS(36,"购物车商品查询成功"),
    COLLECTIOSN_SHOW_SUCCESS(37,"收藏夹展示成功"),
    COLLECTIONS_SAVE_SUCCESS(38,"收藏成功"),
    COLLECTION_SAXE_FAIL(39,"收藏失败"),
    COLLECTIONS_SAVE_TWICE(40,"商品/活动重复收藏"),
    COLLECTIONS_DELETE_FAIL(41,"商品删除失败，收藏夹内已无该商品信息"),
    COLLECTIONS_DELETE_SUCCESS(42,"商品/活动删除成功"),
    USER_NOT_EXIST(45,"查询用户信息失败,用户不存在"),
    USER_FIND_INFO(46,"查询用户成功"),
    USER_INFO_UPDATE_FAIL(47,"用户信息修改失败，表单数据不完整"),
    USER_INFO_NOT_EXIST(48,"用户信息修改失败,用户不在库"),
    USER_INFO_UPDATE_SUCCESS(49,"用户信息修改成功"),
    MESSAGES_FINDALL_SUCCESS(50,"商品留言查询成功"),
    MESSAGE_SAVE_FAIL(51,"留言失败，未填写任何信息"),
    MESSAGES_SAVE_SUCCESS(52,"留言成功"),
    NOTICE_SAVE_FAIL(53,"通知收藏失败"),
    NOTICE_SAVE_SUCCESS(54,"通知信息存储成功"),
    SELLER_FIND_NOTICE(55,"卖家查询交易记录成功"),
    NOTICE_UPDATE_FAIL(56,"通知消息状态修改失败，通知消息不在库"),
    NOTICE_UPDATE_SUCCESS(57,"通知消息状态修改成功"),
    ;
    private Integer code;
    private String message;

    VOResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
