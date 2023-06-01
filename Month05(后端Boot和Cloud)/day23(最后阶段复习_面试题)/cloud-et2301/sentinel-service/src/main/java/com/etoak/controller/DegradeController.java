package com.etoak.controller;

import com.etoak.common.ResultVO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 降级策略
 */
@RestController
@RequestMapping("/degrade")
public class DegradeController {

    /**
     * 测试慢调用比例
     * 单位时间  默认1s
     * 最小请求数
     * 最大响应时间: RT 单位ms
     * 比例阈值: 0-1 的小数
     * 熔断时长
     */
    @RequestMapping("/ratio")
    public ResultVO ratio(int id) throws InterruptedException {
        if (id <= 0) {
            Thread.sleep(250);
        }
        return ResultVO.success("测试慢调用比例降级！");
    }

    /**
     * 异常比例 或 异常数
     */
    @RequestMapping("/ex")
    public ResultVO exception(int id) {
        if (id <= 0) {
            // 抛异常
            int i = 1 / 0;
        }
        return ResultVO.success("测试异常比例 或 异常数降级！");
    }
}
