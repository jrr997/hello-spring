package com.jrr997.reggie.filter;

import com.alibaba.fastjson2.JSON;
import com.jrr997.reggie.common.BaseContext;
import com.jrr997.reggie.common.Result;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.io.IOException;

@Slf4j
@WebFilter(filterName = "loginCheckFilter", urlPatterns = "/*")
@Component
public class LoginCheckFilter  implements Filter {
    AntPathMatcher matcher = new AntPathMatcher();
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String[] urls = new String[] {
            "/employee/login",
            "/employee/logout",
                "/backend/**",
                "/frontend/**"
        };

        Boolean pass = check(urls, request);

        if (pass) {
            filterChain.doFilter(request, response);
            return;
        }


        // 已登录时放行
        if (request.getSession().getAttribute("employee") != null) {
            String empId = request.getSession().getAttribute("employee").toString();
            BaseContext.setCurrentId(empId);

            log.info("已登录");
            filterChain.doFilter(request, response);
            return;
        }

        log.info("未登录");
        // 未登录
        response.getWriter().write(JSON.toJSONString(Result.error("NOTLOGIN")));
    }

    public Boolean check(String[] urls, HttpServletRequest request) {
        for(String url : urls) {
            if (matcher.match(url, request.getRequestURI())) {
                return true;
            }
        }
        return false;
    }
}
