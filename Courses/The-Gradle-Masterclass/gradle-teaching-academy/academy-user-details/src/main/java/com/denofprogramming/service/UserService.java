package com.denofprogramming.service;

import java.util.logging.Logger;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.denofprogramming.model.User;
import com.denofprogramming.repository.UserRepositoryInterface;

@Service
public class UserService implements UserServiceInterface {

	@Inject
	private UserRepositoryInterface userRepository;
	
	
	private static final Logger logger = Logger.getLogger("com.denofprogramming.service.UserService");
	
	@Override
	public User read(User domain) {	
		logger.info("Entered UserService->read with:" + domain);
		return userRepository.read(domain);
	}

	@Override
	public User update(User domain) {	
		return null;
	}

	
}
