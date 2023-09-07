package com.etoak.config;

import com.etoak.common.constant.QueueConstant;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.CustomExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Lushisan
 * @Date 2023/6/26 17:02
 * @Title: OrderQueueConfig
 * @Description:
 * @Package com.etoak.config
 */

/**
 * 订单延迟队列
 */
@Configuration
public class OrderQueueConfig {

    @Bean
    public CustomExchange orderExchange() {
        Map<String, Object> args = new HashMap<>();
        args.put("x-delayed-type", "direct");

        return new CustomExchange(QueueConstant.ORDER_EXCHANGE,
                "x-delayed-message",
                true,
                false,
                args);
    }

    @Bean
    public Queue orderQueue() {
        return new Queue(QueueConstant.ORDER_QUEUE);
    }

    @Bean
    public Binding orderBinding(Queue orderQueue, CustomExchange orderExchange) {
        return BindingBuilder.bind(orderQueue)
                .to(orderExchange)
                .with(QueueConstant.ORDER_KEY).noargs();
    }


}
