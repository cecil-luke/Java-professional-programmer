package com.etoak.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class NodeService {

    /**
     * 通过@SentinelResource注解将一个方法定义为 "资源"
     */
    @SentinelResource(value = "nodeA", blockHandler = "nodeHandler")
    public String node(String from) {
        System.out.println("来自：" + from + "的请求");
        return from;
    }

    public String nodeHandler(String from, BlockException e) {
        log.error(e.getMessage(), e);
        return from + "被限流了！";
    }

}
