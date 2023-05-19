package com.etoak.system.service;

import com.etoak.common.vo.LoginVO;
import com.etoak.system.entity.User;

public interface UserService {

  /**
   * 根据用户名获取用户信息
   *
   * @param username
   * @return
   */
  User getUser(String username);

  /**
   * 登录
   *
   * @param loginVO
   * @return token
   */
  String login(LoginVO loginVO);

}
