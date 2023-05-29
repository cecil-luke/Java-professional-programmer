package com.etoak.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.etoak.entity.User;
import com.etoak.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

  /**
   * 引用远程服务
   */
  @Reference(retries = 1, timeout = 3000, check = false)
  UserService userService;

  @GetMapping("/{id}")
  public User getUser(@PathVariable long id) {
    return userService.getUser(id);
  }

  @PostMapping("/add")
  public String addUser(User user) {
    userService.addUser(user);
    return "success";
  }

}
