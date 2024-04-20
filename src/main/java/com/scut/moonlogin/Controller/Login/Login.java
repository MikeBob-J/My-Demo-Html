package com.scut.moonlogin.Controller.Login;

import com.scut.moonlogin.Data.ReDate;
import com.scut.moonlogin.Data.User;
import com.scut.moonlogin.Mapper.DateMapper;
import com.scut.moonlogin.utils.JwtGet;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@Slf4j
@RestController
@CrossOrigin
public class Login{

    @Autowired
    DateMapper dateMapper;
    @PostMapping("/login")
    public Object loginRequest(@RequestBody User user) {
        System.out.println(user);
        // 1.登录验证,成功下发令牌,失败返回错误信息.
        User userInSQL = dateMapper.selectUser(user);

        if (userInSQL == null) {
            return ReDate.error("用户名不存在或密码错误!");
        } else {
            HashMap<String,Object> map = new HashMap<>();
            map.put("user",user);
            String userJwt = JwtGet.generateJwt(map);

            return ReDate.success(userJwt);
        }
    }
}