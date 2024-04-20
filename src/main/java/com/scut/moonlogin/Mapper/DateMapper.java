package com.scut.moonlogin.Mapper;


import com.scut.moonlogin.Data.Note;
import com.scut.moonlogin.Data.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Mapper
public interface DateMapper {

    // 删除笔记
    @Delete(" delete from ${user} where id = #{id}")
     Integer delete(String user,Integer id);
    // 添加笔记
     void insert(Note note);
    // 用户查询
    @Select("select * from users where username = '${userName}' and password = '${passWord}'")
    User
    selectUser(User user);
    // 添加新用户
    @Insert("insert into users (username, user_id, password) VALUES " +
            "(#{userName},null,#{passWord})")
    void newUser(User user);
    // 添加新用户-笔记
    void createUserNote(User user);

    // 查找所有笔记
    List<Note> findAll(User user);

    void ClsGo(User userName);

}
