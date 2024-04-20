package com.scut.moonlogin.Data;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReDate {
    private Integer code;//响应码，1 代表成功; 0 代表失败
    private String msg;  //响应信息
    private Object data; //返回的数据
    //增删改 成功响应
    public static ReDate success(){
        return new ReDate(1,"操作成功",null);
    }
    //查询 成功响应
    public static ReDate success(Object data){
        return new ReDate(1,"操作成功",data);
    }
    //失败响应
    public static ReDate error(String msg){
        return new ReDate(0,msg,null);
    }
}
