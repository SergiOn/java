package com.ldeng.service;

import com.ldeng.model.User;

import java.util.List;

public interface UserService {

    List<User> findAllUsers();

    User findByUserName(String userName);

    User save(User user);

}
