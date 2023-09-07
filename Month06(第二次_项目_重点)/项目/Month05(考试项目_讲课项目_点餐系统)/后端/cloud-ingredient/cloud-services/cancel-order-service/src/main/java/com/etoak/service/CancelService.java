package com.etoak.service;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.etoak.common.constant.CommonConstant;
import com.etoak.common.constant.QueueConstant;
import com.etoak.entity.Order;
import com.etoak.mapper.OrderMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author Lushisan
 * @Date 2023/6/26 19:08
 * @Title: CancelService
 * @Description:
 * @Package com.etoak.service
 */

@Service
@Slf4j
public class CancelService {

    @Autowired
    OrderMapper orderMapper;

    @RabbitListener(queues = QueueConstant.ORDER_QUEUE)
    public void cancelOrder(String orderNo) {
        log.info("orderNo is {}", orderNo);
        Order order = this.getOrder(orderNo);

        //　如果订单不为空，　并且是新建状态
        if (ObjectUtils.isNotEmpty(order)
            && order.getOrderState().equals(CommonConstant.ORDER_CREATE)) {

            // 取消订单
            order.setOrderState(CommonConstant.ORDER_CANCEL);
            order.setCancelTime(DateUtil.now());
            order.setCancelReason("超时未支付，自动取消！");
            orderMapper.updateById(order);
        }
    }

    /**
     * 查询订单
     * @param orderNo
     * @return
     */
    private Order getOrder(String orderNo) {
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("order_no", orderNo);
        return orderMapper.selectOne(queryWrapper);
    }
}
