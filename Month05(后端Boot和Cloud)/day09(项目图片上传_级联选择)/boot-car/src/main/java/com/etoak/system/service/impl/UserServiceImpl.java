package com.etoak.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.etoak.system.entity.User;
import com.etoak.system.mapper.UserMapper;
import com.etoak.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  UserMapper userMapper;

  @Override
  public User getUser(String username) {
    QueryWrapper<User> queryWrapper = new QueryWrapper<>();
    queryWrapper.eq("username", username);
    return userMapper.selectOne(queryWrapper);
  }
}
