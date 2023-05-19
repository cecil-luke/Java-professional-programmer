package com.etoak.system.service.impl;

import cn.hutool.crypto.digest.MD5;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.etoak.common.exception.ParamException;
import com.etoak.common.utils.JwtUtil;
import com.etoak.common.utils.ValidatorUtil;
import com.etoak.common.vo.LoginVO;
import com.etoak.system.controller.CodeController;
import com.etoak.system.entity.User;
import com.etoak.system.mapper.UserMapper;
import com.etoak.system.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

  @Autowired
  UserMapper userMapper;

  @Autowired
  StringRedisTemplate stringRedisTemplate;

  @Override
  public User getUser(String username) {
    QueryWrapper<User> queryWrapper = new QueryWrapper<>();
    queryWrapper.eq("username", username);
    return userMapper.selectOne(queryWrapper);
  }

  @Override
  public String login(LoginVO loginVO) {
    ValidatorUtil.validate(loginVO);

    String uuid = loginVO.getUuid();
    String code = loginVO.getCode();

    String key = CodeController.KEY_PREFIX + uuid;
    String savedCode = stringRedisTemplate.opsForValue().get(key);

    if (!StringUtils.equalsIgnoreCase(code, savedCode)) {
      throw new ParamException("验证码错误或验证码已过期！");
    }

    User user = getUser(loginVO.getUsername());
    if (ObjectUtils.isEmpty(user)) {
      log.warn("没有这个用户！");
      throw new ParamException("用户名或密码错误");
    }

    // 验证密码
    String password = MD5.create().digestHex(loginVO.getPassword());
    if (!StringUtils.equals(password, user.getPassword())) {
      throw new ParamException("用户名或密码错误");
    }

    // 返回JWT
    return JwtUtil.createJwt(user.getUsername());
  }
}
