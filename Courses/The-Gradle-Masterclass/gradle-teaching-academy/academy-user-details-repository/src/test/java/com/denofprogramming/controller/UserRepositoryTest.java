package com.denofprogramming.controller;

import static org.junit.Assert.assertNotNull;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.denofprogramming.config.UserTestConfig;
import com.denofprogramming.model.User;
import com.denofprogramming.repository.UserRepositoryInterface;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=UserTestConfig.class)
public class UserRepositoryTest {
	
	@Inject
	private UserRepositoryInterface userRepository;
	
	
	@Test
	public void readStudentTest(){		
		final User domain = new User();
		final User returnUser = userRepository.read(domain);
		assertNotNull("returnUser should not be null", returnUser);
		Assert.assertTrue("user name is not correct", returnUser.getName().equals("Jim Sparrow"));
		Assert.assertTrue("user age is not correct", returnUser.getAge() == 31);
	}
	
}
