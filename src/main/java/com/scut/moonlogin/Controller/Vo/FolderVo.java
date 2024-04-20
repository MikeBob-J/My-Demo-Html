package com.scut.moonlogin.Controller.Vo;

import lombok.Data;

import java.util.List;

@Data
public class FolderVo {
    private String folderName;
    private String modifyDate;
    // 记录文件目录
    private String path;
    // 记录文件子文件夹
    private List<FolderVo> children;

}
