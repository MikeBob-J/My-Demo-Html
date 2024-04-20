package com.scut.moonlogin.Controller.Vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileVo {

    private String fileName;
    private String type;
    private String modifyDate;
    private String parentPath;
    // 用于排序的 modifyDateSort 字段
    private long modifyDateSort;
}
