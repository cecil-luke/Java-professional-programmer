package com.etoak.controller;

import com.etoak.properties.UserProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope // 自动刷新注册中心的配置项
public class TestController {

    @Value("${user.id}")
    private int id;

    @Value("${user.name}")
    private String name;

    @Autowired
    UserProperties userProperties;

    @RequestMapping("/test")
    public Object test() {
        return "user id=" + id + "; user name=" + name;
        // return "user id=" + userProperties.getId()  + "; user name=" + userProperties.getName();
    }
}
