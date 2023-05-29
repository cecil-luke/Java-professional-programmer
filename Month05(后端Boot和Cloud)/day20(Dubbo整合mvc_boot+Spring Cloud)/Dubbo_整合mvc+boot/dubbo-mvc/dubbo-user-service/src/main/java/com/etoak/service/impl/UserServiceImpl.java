package com.etoak.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.crypto.digest.MD5;
import com.alibaba.dubbo.config.annotation.Service;
import com.etoak.entity.User;
import com.etoak.mapper.UserMapper;
import com.etoak.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * Dubbo的@Service注解用于发布服务, 相当于
 * <dubbo:service ref="" interface=""></dubbo:service>
 */
@Service
public class UserServiceImpl implements UserService {

  @Autowired
  UserMapper userMapper;

  @Override
  public User getUser(long id) {
    return userMapper.selectById(id);
  }

  @Transactional
  @Override
  public boolean addUser(User user) {
    user.setPassword(MD5.create().digestHex("123456"));
    user.setCreateTime(DateUtil.now());
    return userMapper.insert(user) > 0;
  }

}
