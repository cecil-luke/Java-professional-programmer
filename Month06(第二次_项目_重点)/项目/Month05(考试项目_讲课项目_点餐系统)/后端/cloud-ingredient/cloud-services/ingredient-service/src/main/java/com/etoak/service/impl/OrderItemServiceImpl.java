package com.etoak.service.impl;

import com.etoak.entity.OrderItem;
import com.etoak.mapper.OrderItemMapper;
import com.etoak.service.OrderItemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author et2302
 * @since 2023-06-26
 */
@Service
public class OrderItemServiceImpl extends ServiceImpl<OrderItemMapper, OrderItem> implements OrderItemService {

}
