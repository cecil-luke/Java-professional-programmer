package com.etoak.service;

import cn.hutool.json.JSONUtil;
import com.etoak.common.vo.Email;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * @Author Lushisan
 * @Date 2023/6/25 18:16
 * @Title: EmailService
 * @Description:
 * @Package com.etoak.service
 */
@Service
public class EmailService {

    /**
     * 邮件发送对象
     */
    @Autowired
    JavaMailSender mailSender;

    /**
     * 发件人
     */
    @Value("${spring.mail.username}")
    private String from;

    @RabbitListener(queues = "email.queue")
    public void consume2303(String msg) {
        System.out.println("打印队列上的消息:" + msg);

        // 将MQ的JSON消息反序列化为Email对象
        Email email = JSONUtil.toBean(msg, Email.class);

        // 创建邮件消息
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        // 发件人
        mailMessage.setFrom(this.from);

        // 收件人
        mailMessage.setTo(email.getReceiver(),"431103832@qq.com");

        // 主题
        mailMessage.setSubject(email.getSubject());

        // 邮件内容
        mailMessage.setText(email.getContent());

        mailSender.send(mailMessage);
        System.out.println("发送结束！");

    }
}
