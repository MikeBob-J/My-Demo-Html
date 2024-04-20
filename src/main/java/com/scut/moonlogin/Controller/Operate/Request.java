package com.scut.moonlogin.Controller.Operate;

import com.scut.moonlogin.Data.Note;
import com.scut.moonlogin.Data.ReDate;
import com.scut.moonlogin.Data.User;
import com.scut.moonlogin.Mapper.DateMapper;
import com.scut.moonlogin.Service.DateService;
import com.scut.moonlogin.utils.JwtGet;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.logging.Logger;
@Slf4j
@RestController
@CrossOrigin
public class Request{
    //private static final Logger log = (Logger) LoggerFactory.getLogger(Request.class);

    @Autowired
    DateMapper dateMapper;
    @Autowired
    DateService dateService;

    // Get请求
    @PostMapping( "/delete")
    public ReDate delete(@RequestBody Note note) {
        log.info("删除笔记");
        Integer m = dateMapper.delete(note.getUserName(),note.getId());
        if(m != 0){

            return ReDate.success(dateService.findAll(new User(note.getUserName(),null,null)));
        }else{
            log.info("删除"+ note +"失败");
            return ReDate.error("删除失败!");
        }
    }

    @PostMapping("/insert")
    public  ReDate insert(@RequestBody Note note) {
        log.info("笔记添加");
        User user = new User();
        user.setUserName(note.getUserName());
        // System.out.println(note + "这是笔记内容");
        dateMapper.insert(note);
        return ReDate.success(dateService.findAll(user));
    }

    @PostMapping("/select")
    public  ReDate select(@RequestBody User user) {
        log.info("查找所有笔记");

        return ReDate.success(dateService.findAll(user));
    }

    @PostMapping("/cls")
    public ReDate Cls(@RequestBody User user){
        // System.out.println(userName);
        dateMapper.ClsGo(user);
        return ReDate.success(dateService.findAll(user));
    }

    @PostMapping("/login/new")
    public Object NewUser(@RequestBody User user){
        try {
            if(dateMapper.selectUser(user) == null){
                dateMapper.newUser(user);
                dateMapper.createUserNote(user);
                return ReDate.success();
            }else{
                return ReDate.error("用户名已存在!");
            }
        }catch(Exception e){
            return ReDate.error("注册失败!请检查输入?");
        }

    }


}
