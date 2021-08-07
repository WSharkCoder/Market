package cn.wsharkcoder.marcket.service.impl;


import cn.wsharkcoder.marcket.dataobject.ActivityImg;
import cn.wsharkcoder.marcket.repository.ActivityImgsRepository;
import cn.wsharkcoder.marcket.service.ActivityImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created By 方俊雄
 *
 * @date 2019/7/22 23:24
 */
@Service
public class ActivityImgServiceImpl implements ActivityImgService {
   @Autowired
   private ActivityImgsRepository activityImgsRepository;
    @Override
    public List<String> getImgs(int id) {
        List<String> list=new ArrayList<>();
        List<ActivityImg> list1=activityImgsRepository.findAllByActivityId(id);
        for(ActivityImg activityImg:list1){
            list.add(activityImg.getImgUrl());
        }
      return list;
    }

    @Override
    public void saveImgs(int id, List<String> imgs) {
        for(String img:imgs){
            ActivityImg activityImg=new ActivityImg();
            activityImg.setImgUrl(img);
            activityImg.setActivityId(id);
            activityImgsRepository.save(activityImg);
        }
    }


}
