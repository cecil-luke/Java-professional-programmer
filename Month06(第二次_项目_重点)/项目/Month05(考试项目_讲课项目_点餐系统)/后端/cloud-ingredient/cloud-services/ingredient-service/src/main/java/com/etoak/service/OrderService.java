package com.etoak.service;

import com.etoak.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;
import com.etoak.vo.CartVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author et2302
 * @since 2023-06-26
 */
public interface OrderService extends IService<Order> {

    /**
     * 创建订单
     * @param cartVO
     */
    void createOrder(CartVO cartVO);
}
