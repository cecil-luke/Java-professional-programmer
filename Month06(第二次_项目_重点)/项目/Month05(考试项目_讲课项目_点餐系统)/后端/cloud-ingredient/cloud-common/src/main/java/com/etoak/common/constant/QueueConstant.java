package com.etoak.common.constant;

/**
 * @Author Lushisan
 * @Date 2023/6/25 17:27
 * @Title: QueueConstant
 * @Description:
 * @Package com.etoak.common.constant
 */
public class QueueConstant {

    /**
     * 邮件交换机名称
     */
    public static final String EMAIL_EXCHANGE = "email.exchange";

    /**
     * 邮件队列名称
     */
    public static final String EMAIL_QUEUE = "email.queue";

    /**
     * 绑定邮件交换机和邮件队列的routing key
     */
    public static final String EMIL_KEY = "email";

    /**
     * 订单交换机名称
     */
    public static final String ORDER_EXCHANGE = "order.exchage";

    /**
     * 订单队列名称
     */
    public static final String ORDER_QUEUE = "order.queue";

    /**
     * 绑定订单交换机和订单队列的routing　key
     */
    public static final String ORDER_KEY = "order";
}
