package com.itemsharing.userservice.controller;

import com.itemsharing.userservice.model.User;
import com.itemsharing.userservice.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/user")
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/{username}")
	public User getUserByUsername(@PathVariable String username) {
		logger.debug("Entering the user-service-controller ");
		
		return userService.getUserByUsername(username);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public User createUser(@RequestBody User user) {
		return userService.createUser(user);
	}
}
