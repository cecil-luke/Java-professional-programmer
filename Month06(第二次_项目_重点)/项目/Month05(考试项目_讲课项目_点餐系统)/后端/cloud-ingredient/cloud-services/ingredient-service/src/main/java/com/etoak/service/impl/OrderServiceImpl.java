package com.etoak.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.etoak.common.constant.CommonConstant;
import com.etoak.common.constant.QueueConstant;
import com.etoak.common.exception.ParamException;
import com.etoak.context.LoginUserContext;
import com.etoak.entity.Cart;
import com.etoak.entity.Order;
import com.etoak.entity.OrderItem;
import com.etoak.entity.User;
import com.etoak.mapper.OrderItemMapper;
import com.etoak.mapper.OrderMapper;
import com.etoak.service.CartService;
import com.etoak.service.OrderItemService;
import com.etoak.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.etoak.vo.CartVO;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author et2302
 * @since 2023-06-26
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Autowired
    CartService cartService;

    @Autowired
    OrderItemService orderItemService;

    @Autowired
    OrderItemMapper orderItemMapper;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Transactional
    @Override
    public void createOrder(CartVO cartVO) {

        if (StringUtils.isEmpty(cartVO.getPrepareTime()) || CollectionUtils.isEmpty(cartVO.getCartList())) {
            throw new ParamException("预制时间不能为空！");
        }

        List<Cart> catList = cartVO.getCartList();
        // 如果前端传入的预制菜品为空，尝试从redis中获取
        if (CollectionUtils.isEmpty(catList)) {
            catList = cartService.list();

            // 从redis中也没有获取数据
            if (CollectionUtils.isEmpty(catList)) {
                throw new ParamException("没有获取要预制的菜品列表！");
            }
        }

        // 分布式id - 雪花算法
        String orderNo = IdUtil.getSnowflake().nextIdStr();

        // 登录用户
        User loginUser = LoginUserContext.getLoginUser();

        // 创建Order
        Order order = new Order();
        order.setOrderNo(orderNo);
        order.setUserId(loginUser.getId());
        order.setOrderState(CommonConstant.ORDER_CREATE);
        order.setCreateTime(DateUtil.now());
        order.setPrepareTime(cartVO.getPrepareTime());

        // 保存订单
        this.save(order);

        // 创建OrderItem列表
        List<OrderItem> orderItemList = catList.stream().map(cart -> {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrderNo(orderNo);
            orderItem.setIngredientId(cart.getId());
            orderItem.setIngredientName(cart.getName());
            orderItem.setIngredientNum(cart.getNum());
            return orderItem;
        }).collect(Collectors.toList());

        // 保存OrderItem列表
        orderItemMapper.insertItemList(orderItemList);

        // 删除redis中的数据(删除key="cart:用户id")
        String key = CommonConstant.REDIS_CART_PREFIX + loginUser.getId();
        stringRedisTemplate.delete(key);

        // 向队列中发送延迟消息
        rabbitTemplate.convertAndSend(QueueConstant.ORDER_EXCHANGE,
                QueueConstant.ORDER_KEY,
                orderNo,
                message -> {
                    message.getMessageProperties().setDelay(1000 * 30);
                    return message;
                });

    }
}
