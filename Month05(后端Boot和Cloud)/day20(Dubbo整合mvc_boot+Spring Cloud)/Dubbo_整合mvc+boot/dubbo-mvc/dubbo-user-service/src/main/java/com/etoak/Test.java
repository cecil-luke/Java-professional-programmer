package com.etoak;

import com.etoak.mapper.UserMapper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

  public static void main(String[] args) {
    ApplicationContext ioc =
      new ClassPathXmlApplicationContext("spring-root.xml");

    UserMapper userMapper = ioc.getBean(UserMapper.class);

    System.out.println(userMapper.selectById(1));
  }
}
