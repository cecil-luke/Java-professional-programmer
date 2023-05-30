package com.etoak.controller;

import com.etoak.common.ResultVO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试流量控制
 */
@RestController
@RequestMapping("/flow")
public class FlowController {

    @RequestMapping("/hello")
    public ResultVO hello() {
        return ResultVO.success("测试直接限流！");
    }

}
