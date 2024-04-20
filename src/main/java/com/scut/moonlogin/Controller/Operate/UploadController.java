package com.scut.moonlogin.Controller.Operate;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
@CrossOrigin
@RestController
public class UploadController {
    @PostMapping("/upload")
    public void upload(@RequestParam("file") MultipartFile file,
                       @RequestParam("targetPath") String targetPath){
        // 表单项的名字
        System.out.println("form 定义的name值 = " + file.getName());
        // 文件名
        System.out.println("原始文件名 = " + file.getOriginalFilename());

        try {
            // 保存文件到服务器
            file.transferTo(new File(targetPath + file.getOriginalFilename()));
            System.out.println("上传成功,请求地址:" + targetPath + file.getOriginalFilename());
        } catch (Exception e) {
            System.out.println("上传失败");
        }
    }
}
