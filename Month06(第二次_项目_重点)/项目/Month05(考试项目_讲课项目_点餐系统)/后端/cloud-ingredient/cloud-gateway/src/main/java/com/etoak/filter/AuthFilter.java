package com.etoak.filter;

import cn.hutool.json.JSONUtil;
import com.etoak.common.util.JwtUtil;
import com.etoak.common.vo.Result;
import com.etoak.properties.WhiteListProperties;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @Author Lushisan
 * @Date 2023/6/21 10:25
 * @Title: AuthFilter
 * @Description:
 * @Package com.etoak.filter
 */
/**
 * 网关的全局过滤器(应用于所有的Route，无需配置)
 */
@Service
@Slf4j
public class AuthFilter implements GlobalFilter, Ordered {

    @Autowired
    WhiteListProperties whiteListProperties;    // 白名单

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();

        String uri = request.getPath().value();
        log.info("请求URI - {}", uri);

        /* 放行白名单请求 */
        if (whiteListProperties.getUrls().contains(uri) || uri.indexOf("/pics") >= 0) {
            return chain.filter(exchange);
        }

        /* 验证有没有携带token */
        String authorization = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        if (StringUtils.isEmpty(authorization)) {
            return notAuth(response, "请传入token认证信息！");
        }

        try {
            // 获取token中的用户名
            String username = JwtUtil.getUsername(authorization);

            /* 将用户名放入请求头中 mutate 突变 转变*/
            request.mutate().header("username", username);
        }catch(ExpiredJwtException e) {
            return notAuth(response, "token过期！");
        }catch (Exception e) {
            log.error(e.getMessage(), e);
            return notAuth(response, "token错误！");
        }

        return chain.filter(exchange.mutate().request(request).build());
    }

    private Mono<Void> notAuth(ServerHttpResponse response, String message) {
        // 响应结果类型：application/json
        response.getHeaders().set(org.springframework.http.HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        // 响应码
        response.setStatusCode(HttpStatus.OK);

        Result<Object> result = Result.failed(403, message);
        String json = JSONUtil.toJsonStr(result);

        DataBuffer dataBuffer = response.bufferFactory().wrap(json.getBytes());
        return response.writeWith(Mono.just(dataBuffer));
    }

    @Override
    public int getOrder() {
        // 数值越小，越靠前
        return -100;
    }
}
