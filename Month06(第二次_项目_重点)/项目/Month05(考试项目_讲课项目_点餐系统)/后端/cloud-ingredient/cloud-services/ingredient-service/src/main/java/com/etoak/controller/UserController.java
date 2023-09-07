package com.etoak.controller;


import com.etoak.common.vo.Result;
import com.etoak.entity.User;
import com.etoak.service.UserService;
import com.etoak.vo.UserVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 前端控制器
 *
 * @author et2302
 * @since 2023-06-20
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/login")
    public Result login(UserVO userVO) {
        String token = userService.login(userVO);
        return Result.success(token);
    }

    /**
     * 注册接口
     */
    @PostMapping("/register")
    public Result register(@RequestBody User user) {
        userService.register(user);
        return Result.success(StringUtils.EMPTY);
    }

    /**
     * 退出系统
     * @return
     */
    @GetMapping("/logout")
    public Result logout() {
        userService.logout();
        return Result.success(StringUtils.EMPTY);
    }


}

