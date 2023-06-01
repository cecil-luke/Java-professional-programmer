package com.etoak.handler;

import cn.hutool.json.JSONUtil;
import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowException;
import com.etoak.common.ResultVO;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@Service
public class MyBlockHandler implements BlockExceptionHandler {

    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       BlockException e) throws Exception {

        String uri = request.getRequestURI();
        response.setContentType("application/json;charset=utf-8");

        ResultVO resultVO = null;

        if (e instanceof FlowException) {
            resultVO = ResultVO.failed(uri + "超出最大请求数！");
        }
        else if (e instanceof DegradeException) {
            resultVO = ResultVO.failed(uri + "降级！");
        }
        else if (e instanceof ParamFlowException) {
            resultVO = ResultVO.failed(uri + "热点参数限流了！");
        }
        else {
            resultVO = ResultVO.failed("系统异常！");
        }

        String json = JSONUtil.toJsonStr(resultVO);
        PrintWriter writer = response.getWriter();
        writer.print(json);
        writer.flush();
        writer.close();
    }
}
