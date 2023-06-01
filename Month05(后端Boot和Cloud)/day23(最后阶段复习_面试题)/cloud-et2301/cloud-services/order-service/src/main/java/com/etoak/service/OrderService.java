package com.etoak.service;

import com.etoak.entity.Order;

public interface OrderService {

    /**
     * 创建订单
     *
     * @param order
     */
    void create(Order order);

    /**
     * 获取订单
     *
     * @param id
     * @return
     */
    Order getOrder(int id);
}
