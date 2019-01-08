package com.springliquibase.authorizationservice.service;

import com.springliquibase.authorizationservice.model.User;

public interface UserService {

    User createDefaultUser(User user);
    User saveUser(User user);
    User getUserByUsername(String username);
    boolean isUserNotExist(User user);

}
