package com.etoak.service;

import com.etoak.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.etoak.vo.UserVO;

/**
 * UserService服务类
 *
 * @author et2302
 * @since 2023-06-20
 */
public interface UserService extends IService<User> {

    /**
     * 登录
     *
     * @param userVO
     * @return
     */
    String login(UserVO userVO);

    /**
     * 根据用户名查询用户信息
     *
     * @param username
     * @return
     */
    User getUser(String username);

    /**
     * 登录注册
     * @return
     */
    boolean register(User user);

    /**
     * 退出系统
     */
    void logout();
}
