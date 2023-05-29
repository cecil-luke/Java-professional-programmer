package com.etoak.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.etoak.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

  @Reference(check = false)
  UserService userService;

  @GetMapping("/{id}")
  public Object getUser(@PathVariable long id) {
    return userService.getUser(id);
  }

  @GetMapping("/list")
  public Object getList(@RequestParam String name) {
    return userService.getList(name);
  }
}
