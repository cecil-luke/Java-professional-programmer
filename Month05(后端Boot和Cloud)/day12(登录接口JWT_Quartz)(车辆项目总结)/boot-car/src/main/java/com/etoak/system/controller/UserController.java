package com.etoak.system.controller;

import com.etoak.common.vo.LoginVO;
import com.etoak.common.vo.ResultVO;
import com.etoak.system.entity.User;
import com.etoak.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

  @Autowired
  UserService userService;

  @GetMapping("/getUser")
  public User getUser(String username) {
    return userService.getUser(username);
  }

  /**
   * 登录接口
   *
   * @param loginVO
   */
  @PostMapping("/login")
  public ResultVO login(LoginVO loginVO) {
    String token = userService.login(loginVO);
    return ResultVO.success(token);
  }


}
