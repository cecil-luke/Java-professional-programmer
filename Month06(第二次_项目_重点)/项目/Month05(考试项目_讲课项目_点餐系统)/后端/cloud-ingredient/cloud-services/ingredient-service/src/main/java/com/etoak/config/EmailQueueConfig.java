package com.etoak.config;

import com.etoak.common.constant.QueueConstant;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author Lushisan
 * @Date 2023/6/25 17:26
 * @Title: EmailQueueConfig
 * @Description:
 * @Package com.etoak.config
 */

/**
 * 配置邮件交换机和队列
 */
@Configuration
public class EmailQueueConfig {

    @Bean
    public DirectExchange emailExchange() {
        return new DirectExchange(QueueConstant.EMAIL_EXCHANGE);
    }

    @Bean
    public Queue emailQueue() {
        return new Queue(QueueConstant.EMAIL_QUEUE);
    }

    @Bean
    public Binding emailBinding(Queue emailQueue, DirectExchange emailExchange) {
        return BindingBuilder.bind(emailQueue)
                .to(emailExchange)
                .with(QueueConstant.EMIL_KEY);
    }
}
