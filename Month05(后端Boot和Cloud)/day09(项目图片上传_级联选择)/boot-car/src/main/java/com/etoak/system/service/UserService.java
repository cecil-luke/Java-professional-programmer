package com.etoak.system.service;

import com.etoak.system.entity.User;

public interface UserService {

  /**
   * 根据用户名获取用户信息
   *
   * @param username
   * @return
   */
  User getUser(String username);

}
