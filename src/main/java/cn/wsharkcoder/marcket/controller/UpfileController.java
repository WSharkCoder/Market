package cn.wsharkcoder.marcket.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;


/**
 * Created By 方俊雄
 *
 * @date 2019/7/22 13:01
 */
@Controller
@Slf4j
public class UpfileController{
        @RequestMapping("/upfile")
        @ResponseBody
            public String upfile(@RequestParam(name = "file") MultipartFile multipartFile) {
            //指定存放文件路径
            String fileDir = "/usr/local/src/static/img";
            File dir = new File(fileDir);
            //判断目录是否存在，不存在则创建目录
            if (!dir.exists()) {
                dir.mkdir();
            }
            /**生成新的文件名，防止文件名重复而导致文件覆盖*/
            //1.获取源文件的后缀名
            String originalFileName = multipartFile.getOriginalFilename();
            String suffix = originalFileName.substring(originalFileName.lastIndexOf('.'));
            //2.使用UUID生成新的文件名
            String newFileName = UUID.randomUUID() + suffix;
            //生成文件
            //生成文件
            File file = new File(dir, newFileName);
            try {
                //传输文件内容
                multipartFile.transferTo(file);
                System.out.println("文件上传成功");
            } catch (IOException e) {
                System.out.println("上传文件失败");
                e.printStackTrace();
                return "";
            }
            return "http://129.211.66.191/" + newFileName;
        }
}
