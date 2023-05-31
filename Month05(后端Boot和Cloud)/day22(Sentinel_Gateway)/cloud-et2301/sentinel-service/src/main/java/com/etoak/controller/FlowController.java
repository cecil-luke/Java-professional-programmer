package com.etoak.controller;

import com.etoak.common.ResultVO;
import com.etoak.service.NodeService;
import org.springframework.beans.factory.annotation.Autowired;
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

    /******* 测试流控模式：关联限流 *********/
    @RequestMapping("/read")
    public ResultVO read() {
        return ResultVO.success("读 数据库！");
    }

    @RequestMapping("/write")
    public ResultVO write() {
        return ResultVO.success("写 数据库！");
    }
    /******* 测试流控模式：关联限流 *********/

    /******* 测试流控模式：链路限流 start *********/
    @Autowired
    NodeService nodeService;

    @RequestMapping("/entrance1")
    public ResultVO entrance1() {
        String result = nodeService.node("entrance1");
        return ResultVO.success(result);
    }

    @RequestMapping("/entrance2")
    public ResultVO entrance2() {
        String result = nodeService.node("entrance2");
        return ResultVO.success(result);
    }
    /******* 测试流控模式：链路限流 end *********/

    /******* 测试流控效果：Warm Up start *********/
    @RequestMapping("/warm")
    public ResultVO warm() {
        return ResultVO.success("预热或冷启动！");
    }
    /******* 测试流控效果：Warm Up end *********/

    /******* 测试流控效果：排队等待(匀速器) start *********/
    @RequestMapping("/queue")
    public ResultVO queue() {
        return ResultVO.success("排队等待！");
    }
    /******* 测试流控效果：排队等待(匀速器) end *********/
}
