package com.ldeng.service;

import java.util.List;

import com.ldeng.model.User;

public interface UserService {
	List<User> findAllUsers();
	
	User findByUserName(String userName);
	
	User save(User user);

}
