package com.etoak.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.etoak.common.ResultVO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试热点参数限流
 * 参数不能是对象
 * 支持参数类型: byte int long float double char String
 */
@RestController
@RequestMapping("/product")
public class HotParamController {

    @SentinelResource(value = "product", blockHandler = "getProductHandler")
    @RequestMapping("/{id}")
    public ResultVO getProduct(@PathVariable long id) {
        return ResultVO.success("商品id：" + id);
    }

    public ResultVO getProductHandler(long id, BlockException e) {
        return ResultVO.failed("热点参数限流！");
    }
}
