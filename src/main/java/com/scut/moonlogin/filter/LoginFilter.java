package com.scut.moonlogin.filter;

import com.alibaba.fastjson.JSONObject;
import com.scut.moonlogin.Data.ReDate;
import com.scut.moonlogin.utils.JwtGet;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.util.StringUtils;

import java.io.IOException;

@Slf4j
@WebFilter(urlPatterns = "/1214")
public  class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest Request, ServletResponse Response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) Request;
        HttpServletResponse resp = (HttpServletResponse) Response;
        // 发两次请求啊!
        if (HttpMethod.OPTIONS.toString().equals(req.getMethod())) {
            System.out.println("OPTIONS请求");
            filterChain.doFilter(Request,Response);
            return;
        }
        // 暂时关闭拦截器


        //1.获取请求url,用于筛选通行网页
        String url = req.getRequestURL().toString();
        if(url.contains("login")){
            String jwt = req.getHeader("Token");
            System.out.println(jwt + "\n令牌");
            log.info("登录操作");
            filterChain.doFilter(Request,Response);
            return;
        }else if(url.contains("new")){
            log.info("注册操作");
            filterChain.doFilter(Request,Response);
            return;
        }

        String jwt = req.getHeader("Token");
        System.out.println(jwt + "\n令牌");
        if(!StringUtils.hasLength(jwt)){
            log.info("未登录");
            ReDate error = ReDate.error("NOT_LOGIN");
            String notLogin = JSONObject.toJSONString(error);
            resp.getWriter().write(notLogin);
            return;
        }

        try {
            JwtGet.parseJWT(jwt);
        } catch (Exception e) {//jwt解析失败
            // e.printStackTrace();// 打印报错
            log.info("解析令牌失败, 未登录");
            ReDate error = ReDate.error("NOT_LOGIN");
            String notLogin = JSONObject.toJSONString(error);
            resp.getWriter().write(notLogin);
            return;
        }

        log.info("令牌合法,");
        filterChain.doFilter(Request,Response);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
