package com.wmj.mmall.filter;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

//过滤器判断 user 的session是否存在 解决不登陆用户 添加购物车报错的bug  他有一个config的相当于配置类
public class UserFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        if (session.getAttribute("user") == null){
            response.sendRedirect("/login");
        }else {
            //继续执行的意思 filterChain.doFilter(servletRequest,servletResponse);
            filterChain.doFilter(servletRequest,servletResponse);
        }
    }
}
