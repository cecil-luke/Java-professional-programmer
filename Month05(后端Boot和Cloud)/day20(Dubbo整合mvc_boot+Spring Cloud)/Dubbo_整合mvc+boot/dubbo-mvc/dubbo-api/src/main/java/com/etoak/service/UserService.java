package com.etoak.service;

import com.etoak.entity.User;

/**
 * Dubbo服务
 */
public interface UserService {

  User getUser(long id);

  boolean addUser(User user);

}
