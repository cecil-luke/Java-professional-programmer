package com.etoak.service;

import com.etoak.entity.Order;

public interface OrderService {

  /**
   * 创建订单
   *
   * @param order
   */
  void create(Order order);

}
