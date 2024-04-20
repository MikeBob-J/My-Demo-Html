package com.scut.moonlogin;

import com.alibaba.fastjson.JSON;
import com.scut.moonlogin.Data.User;
import com.scut.moonlogin.Mapper.DateMapper;
import com.scut.moonlogin.Service.DateService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.awt.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class MoonLoginApplicationTests {

    @Autowired
    DateService dateService;
    @Test
    public void contextLoads() {

        // dateMapper.selectUser();

        // 测试JWT令牌的生成
//        Map<String, Object> claims = new HashMap<>();
//        claims.put("id", 1);
//        claims.put("name", "lisi");
//
//        String jwt = Jwts.builder()
//                .signWith(SignatureAlgorithm.HS256, "HengLu")
//                .setClaims(claims)
//                .setExpiration(new Date(System.currentTimeMillis() + 3600 * 1000))
//                .compact();
//
//        System.out.println(jwt);
//
//        Claims data = Jwts.parser().setSigningKey("HengLu")
//                .parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoibGlzaSIsImlkIjoxLCJleHAiOjE3MTA0NzQ3NjR9.BCwTL-PMSgC0F6el3MxdFkGl3swAbdvR5D4JteaNFX8")
//                .getBody();
//
//        System.out.println(data);
//
        User user = new User("lisi",1,"123456");
        System.out.println(user);


        System.out.println("这个是:"+ JSON.toJSONString(dateService.findAll(user)));
    }
}
