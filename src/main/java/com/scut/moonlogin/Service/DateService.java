package com.scut.moonlogin.Service;

import com.alibaba.fastjson.JSON;
import com.scut.moonlogin.Data.Note;
import com.scut.moonlogin.Data.User;
import com.scut.moonlogin.Mapper.DateMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class DateService{

    @Autowired
    DateMapper dateMapper;
    public List<String> findAll(User user){
        List<String> list = new ArrayList<>();
        for(Note note:dateMapper.findAll(user)) {
            list.add(JSON.toJSONString(note));
        }
        return list;
    }
}
