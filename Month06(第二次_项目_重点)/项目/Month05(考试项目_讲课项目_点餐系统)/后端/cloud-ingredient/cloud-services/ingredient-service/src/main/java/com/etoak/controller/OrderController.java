package com.etoak.controller;


import com.etoak.common.vo.Result;
import com.etoak.service.OrderService;
import com.etoak.vo.CartVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author et2302
 * @since 2023-06-26
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    /**
     * 创建订单
     * post / order
     */
    @PostMapping
    public Result createOrder(@RequestBody CartVO cartVO) {
        orderService.createOrder(cartVO);
        return Result.success(StringUtils.EMPTY);
    }
}

