package com.springliquibase.authorizationservice.service;

import com.springliquibase.authorizationservice.model.User;

public interface UserService {

    User createUser(User user);
    User getUserByUsername(String username);

}
