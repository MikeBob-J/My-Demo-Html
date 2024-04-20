package com.scut.moonlogin.Controller.Operate;

import com.scut.moonlogin.Controller.Vo.FileVo;
import com.scut.moonlogin.Controller.Vo.FolderVo;
import com.scut.moonlogin.utils.FileUtil;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@CrossOrigin
@RestController
public class FileController {
    // 测试接口
    // http://localhost:8080/fileList?parentPath=E:/
    @GetMapping("/fileList/{pageNum}/{pageSize}")
    public Map<String, Object> fileList(@RequestParam("parentPath") String parentPath,
                                        @PathVariable("pageNum") Integer pageNum,
                                        @PathVariable("pageSize") Integer pageSize) {
        Map<String, Object> map = new HashMap<>();
        List<FileVo> files = FileUtil.files(parentPath);
        // 返回总文件数
        map.put("size",files.size());
        // 封装返回数据,数据条数为分页准备

        int start = (pageNum - 1) * pageSize;
        int end = pageNum * pageSize;
        // 索引越界
        if (end > files.size()){
            end = files.size();
        }
        files= files.subList(start, end);
        map.put("data",files);
        return map;
    }
    // 获取文件夹列表,parentPath盘符路径
    @GetMapping("/folderList")
    public Map<String, Object> folderList(@RequestParam("parentPath") String parentPath) {
        Map<String, Object> map = new HashMap<>();
        List<FolderVo> folders = FileUtil.folders(parentPath);
        map.put("size", folders.size());
        map.put("data", folders);
        return map;
    }
}
