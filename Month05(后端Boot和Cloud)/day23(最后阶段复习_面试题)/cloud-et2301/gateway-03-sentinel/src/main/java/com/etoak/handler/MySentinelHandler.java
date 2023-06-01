package com.etoak.handler;

import cn.hutool.json.JSONUtil;
import com.etoak.common.ResultVO;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebExceptionHandler;
import reactor.core.publisher.Mono;

/**
 * 网关定制Sentinel异常
 */
@Service
// 最高优先级
@Order(Ordered.HIGHEST_PRECEDENCE)
public class MySentinelHandler implements WebExceptionHandler {

    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
        ServerHttpRequest request = exchange.getRequest();
        // 请求的URI
        String uri = request.getPath().toString();

        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.OK);
        response.getHeaders().set(HttpHeaders.CONTENT_TYPE, "application/json;charset=utf-8");

        ResultVO<Object> resultVO = ResultVO.failed(uri + "达到请求最大限制！");
        String json = JSONUtil.toJsonStr(resultVO);

        DataBuffer dataBuffer = response.bufferFactory().wrap(json.getBytes());

        // response.getWrite().print(json);
        return response.writeWith(Mono.just(dataBuffer));
    }
}
