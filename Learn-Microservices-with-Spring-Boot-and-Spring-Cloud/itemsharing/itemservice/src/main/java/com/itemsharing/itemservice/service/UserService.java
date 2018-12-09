package com.itemsharing.itemservice.service;

import com.itemsharing.itemservice.model.User;

public interface UserService {

    User findByUsername(String username);
//    Optional<User> findByUsername(String username);

}
