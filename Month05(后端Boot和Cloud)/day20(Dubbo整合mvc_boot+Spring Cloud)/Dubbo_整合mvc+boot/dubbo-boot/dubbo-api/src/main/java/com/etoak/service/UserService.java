package com.etoak.service;

import com.etoak.entity.User;

import java.util.List;

public interface UserService {

  User getUser(long id);

  List<User> getList(String name);

}
