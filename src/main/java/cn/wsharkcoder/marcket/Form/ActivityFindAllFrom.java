package cn.wsharkcoder.marcket.Form;

import lombok.Data;

import java.util.List;

/**
 * Created By 方俊雄
 *
 * @date 2019/7/22 13:56
 */
@Data
public class ActivityFindAllFrom {
    private Integer id;
    private String name;
    private String startTime;
    private String endTime;
    /**
     * 详情可为空
     */
    private String detail;
    private String date;
    private String address;
    private String sponsor;
    private Integer numbers;
    private Integer status;
    private List<ActivityImgMap> imglist;
    private String contentInfo;
    private String userimgUrl;
}
