package com.etoak.system.controller;

import com.etoak.system.entity.User;
import com.etoak.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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

}
