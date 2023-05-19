package com.etoak.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 跨域过滤器
 * 跨域: 浏览器的同源策略导致的
 *      可以理解为没有浏览器参与的远程调用就没有跨域
 */
public class CrossFilter implements Filter {

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    System.out.println("初始化跨域过滤器");
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    HttpServletResponse resp = (HttpServletResponse) response;

    // 允许哪些域访问、允许哪些方法访问、允许携带的请求头
    resp.addHeader("Access-Control-Allow-Origin", "*");
    resp.addHeader("Access-Control-Allow-Methods", "OPTIONS, GET, POST, PUT, DELETE");
    resp.addHeader("Access-Control-Allow-Headers", "*");

    // 预检请求的有效时间
    resp.addHeader("Access-Control-Max-Age", "3600");
    chain.doFilter(request, resp);
  }
}
