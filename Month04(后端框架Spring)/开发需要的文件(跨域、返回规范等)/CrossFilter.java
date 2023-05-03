package com.etoak.rst.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
public class CrossFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse resp, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse)resp;
        response.setHeader("Access-Control-Allow-Origin","*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "token,Accept,Accept-Language,Content-Language,Context-Type,Origin,authorization, No-Cache, X-Requested-With, If-Modified-Since, Pragma, Last-Modified, Cache-Control, Expires, Content-Type, X-E4M-With");
        response.setHeader("Access-Control-Expose-Headers","sessionid,token");
        //放行
        filterChain.doFilter(servletRequest,response);
    }

    @Override
    public void destroy() {

    }
}
