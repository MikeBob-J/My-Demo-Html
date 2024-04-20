package com.scut.moonlogin.Controller.Operate;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileInputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@CrossOrigin
@RestController
public class DownloadController {
    // 文件下载接口
    @RequestMapping("/download")
    public ResponseEntity<byte[]> download(@RequestParam("parentPath") String parentPath, @RequestParam("fileName") String fileName) throws Exception {
        //        FileInputStream is = new FileInputStream("G:/test1.txt");
        FileInputStream is = new FileInputStream(parentPath + "/" + fileName);
        byte[] bytes=new byte[is.available()];
        // 一次读取bytes.length个字节 并将读到的字节放入bytes数组中
        is.read(bytes);
        is.close();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Disposition","attachment;filename="+URLEncoder.encode(fileName, StandardCharsets.UTF_8));
        // 将要下载的文件的字节流返回给浏览器
        ResponseEntity res = new ResponseEntity<byte[]>(bytes,httpHeaders, HttpStatus.OK);
        System.out.println("下载:" + URLEncoder.encode(fileName, StandardCharsets.UTF_8));
        return res;
    }
}
