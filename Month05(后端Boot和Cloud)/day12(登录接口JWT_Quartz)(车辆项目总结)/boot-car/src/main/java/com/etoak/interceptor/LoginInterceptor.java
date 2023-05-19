package com.etoak.interceptor;

import cn.hutool.json.JSONUtil;
import com.etoak.common.utils.JwtUtil;
import com.etoak.common.vo.ResultVO;
import com.etoak.system.entity.User;
import com.etoak.system.service.UserService;
import com.sun.org.apache.regexp.internal.RE;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

  public static final String AUTHORIZATION = "Authorization";

  public static final String OPTIONS = "OPTIONS";

  @Autowired
  UserService userService;

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    /* 放行OPTIONS请求（跨域预检请求） */
    String method = request.getMethod();
    if (StringUtils.equals(method, OPTIONS)) {
      return true;
    }

    /* 判断请求头中有没有Authorization */
    String tokenHeader = request.getHeader(AUTHORIZATION);
    if (StringUtils.isEmpty(tokenHeader)) {
      return noAuth(response, "请传入token！");
    }

    /* 解析token获取用户名 */
    try {
      String username = JwtUtil.getUsername(tokenHeader);
      User user = userService.getUser(username);
      if (ObjectUtils.isEmpty(user)) {
        throw new RuntimeException("用户错误！");
      }
    } catch (ExpiredJwtException e) {
      return noAuth(response, "token过期！");
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      return noAuth(response, "token错误！");
    }

    return true;
  }

  private boolean noAuth(HttpServletResponse response, String msg) throws IOException {
    response.setContentType("application/json;charset=utf-8");

    ResultVO resultVO = ResultVO.failed(403, msg);
    String result = JSONUtil.toJsonStr(resultVO);

    PrintWriter writer = response.getWriter();
    writer.print(result);
    writer.flush();
    writer.close();

    return false;
  }

}
