package com.etoak.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.etoak.entity.User;
import com.etoak.mapper.UserMapper;
import com.etoak.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service // 发布服务
public class UserServiceImpl implements UserService {

  @Autowired
  UserMapper userMapper;

  @Override
  public User getUser(long id) {
    return userMapper.selectById(id);
  }

  @Override
  public List<User> getList(String name) {
    QueryWrapper<User> queryWrapper = new QueryWrapper<>();
    queryWrapper.likeRight("username", name);

    return userMapper.selectList(queryWrapper);
  }
}
