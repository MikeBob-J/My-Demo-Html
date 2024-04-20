package com.scut.moonlogin.utils;

import com.scut.moonlogin.Controller.Vo.FileVo;
import com.scut.moonlogin.Controller.Vo.FolderVo;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FileUtil {
    // 官方SimpleDateFormat对象
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    // 返回某目录下的所有文件列表
    public static List<FileVo> files(String parentPath) {


        List<FileVo> res = new ArrayList<>();
        File parentFolder = new File(parentPath);
        if (!parentFolder.isDirectory()) {
            return Collections.emptyList();
        }
        // 文件夹下的所有文件
        File[] files = parentFolder.listFiles();
        if (files == null || files.length == 0) {
            return Collections.emptyList();
        }
        for (File file : files) {
            if (file.isDirectory()) continue;
            FileVo fileVo = new FileVo();
            fileVo.setFileName(file.getName());
            fileVo.setModifyDate(getModifyDate(file));
            fileVo.setType(getFileType(file));
            // 添加设置file的父目录
            fileVo.setParentPath(convert(file.getParent()));
            // 设置文件的修改日期的时间戳，以便于后续比较
            fileVo.setModifyDateSort(file.lastModified());

            res.add(fileVo);
        }
        // 通过List对象的sort方法按照修改日期从大到小排序
        res.sort((item1, item2) -> {
            return Long.compare(item2.getModifyDateSort(), item1.getModifyDateSort());
        });
        return res;
    }

    private static String getFileType(File file) {
        // 返回文件类型 1.txt
        if (file == null) return "";
        String fileName = file.getName();
        String fileType = fileName.substring(fileName.lastIndexOf(".") + 1);
        return fileType;
    }

    private static String getModifyDate(File file) {
        if (file == null) return "";
        String modifyDate = dateFormat.format(file.lastModified());
        return modifyDate;
    }
    // 路径转换,方便前端调用
    private static String convert(String filePath){
        String replace = filePath.replace("\\", "/");
        return replace;
    }

    // 文件夹
    public static List<FolderVo> folders(String parentPath) {

        List<FolderVo> res = new ArrayList<>();

        File parentFolder = new File(parentPath);
        // 是否文件夹
        if (!parentFolder.isDirectory()) {
            return Collections.emptyList();
        }
        // 有否子目录
        File[] files = parentFolder.listFiles();
        if (files == null || files.length == 0) {
            return Collections.emptyList();
        }
        // 遍历
        for (File file : files) {
            if (file.isFile()) continue;
            FolderVo folderVo = new FolderVo();
            folderVo.setFolderName(file.getName());
            folderVo.setModifyDate(getModifyDate(file));
            // 设置路径
            folderVo.setPath(convert(file.getAbsolutePath()));
            // 设置文件夹子目录
            folderVo.setChildren(folderChildren(file.getAbsolutePath()));
            res.add(folderVo);
        }

        return res;
    }

    // 获取文件夹子文件夹,不推荐递归,运行时长太长了
    public static List<FolderVo> folderChildren(String parentPath) {
        List<FolderVo> res = new ArrayList<>();

        File parentFolder = new File(parentPath);
        if (!parentFolder.isDirectory()) {
            return Collections.emptyList();
        }
        File[] files = parentFolder.listFiles();
        if (files == null || files.length == 0) {
            return Collections.emptyList();
        }
        for (File file : files) {
            if (file.isFile()) continue;
            FolderVo folderVo = new FolderVo();
            folderVo.setFolderName(file.getName());
            folderVo.setModifyDate(getModifyDate(file));
            folderVo.setPath(convert(file.getAbsolutePath()));
            res.add(folderVo);
        }

        return res;
    }
}
