package com.etoak.interceptor;

import cn.hutool.json.JSONUtil;
import com.etoak.common.constant.CommonConstant;
import com.etoak.common.vo.Result;
import com.etoak.context.LoginUserContext;
import com.etoak.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author Lushisan
 * @Date 2023/6/21 11:38
 * @Title: LoginUserInterceptor
 * @Description:
 * @Package com.etoak.interceptor
 */

/**
 * 设置登录用户
 */
@Component
@Slf4j
public class LoginUserInterceptor implements HandlerInterceptor {


    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String username = request.getHeader("username");
        // 获取redis中的登录用户
        String userJson = stringRedisTemplate.opsForValue().get(CommonConstant.REDIS_LOGIN_PREFIX + username);
        if(StringUtils.isEmpty(userJson)) {
            return noAuth(response, "认证信息错误或退出登录！");
        }

        User user = JSONUtil.toBean(userJson, User.class);
        // 关联当前线程
        LoginUserContext.setUser(user);

        return true;
    }

    private boolean noAuth(HttpServletResponse response, String message) throws IOException {
        response.setContentType("application/json;charset=utf-8");

        Result<Object> result = Result.failed(403, message);
        String json = JSONUtil.toJsonStr(result);
        PrintWriter writer = response.getWriter();
        writer.print(json);
        writer.flush();
        writer.close();
        return false;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 解除当前线程与登录用户的绑定关系
        LoginUserContext.removeUser();
    }
}
