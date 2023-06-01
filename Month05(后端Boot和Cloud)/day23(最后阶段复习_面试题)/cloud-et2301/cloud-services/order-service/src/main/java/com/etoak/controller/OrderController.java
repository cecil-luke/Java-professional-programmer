package com.etoak.controller;

import com.etoak.common.ResultVO;
import com.etoak.entity.Order;
import com.etoak.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {

  @Autowired
  OrderService orderService;

  @PostMapping("/create")
  public ResultVO create(@RequestBody Order order) {
    orderService.create(order);
    return ResultVO.success("");
  }

  @GetMapping("/{id}")
  public ResultVO getOrder(@PathVariable int id) {
    Order order = orderService.getOrder(id);
    return ResultVO.success(order);
  }

}

