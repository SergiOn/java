package com.itemsharing.userservice;

import brave.sampler.Sampler;
import com.itemsharing.userservice.model.Role;
import com.itemsharing.userservice.model.User;
import com.itemsharing.userservice.model.UserRole;
import com.itemsharing.userservice.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
@EnableEurekaClient
@EnableResourceServer
public class UserserviceApplication implements CommandLineRunner {

	private UserService userService;

	public UserserviceApplication(UserService userService) {
		this.userService = userService;
	}

	public static void main(String[] args) {
		SpringApplication.run(UserserviceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		User user1 = new User();
		user1.setFirstName("Serhii");
		user1.setLastName("On");
		user1.setUsername("SerhiiOn");
		user1.setPassword("12345");
		user1.setEmail("email@mail.com");

		Set<UserRole> userRoles = new HashSet<>();
		Role role1 = new Role();
		role1.setRoleId(1);
		role1.setName("ROLE_USER");
		userRoles.add(new UserRole(user1, role1));

		userService.createUser(user1);
	}

	//spring.sleuth.sampler.percentage .5
	//spring.sleuth.sampler.probability .5  // new
	@Bean
	public Sampler defaultSampler() {
//		return new AlwaysSampler();
		return Sampler.ALWAYS_SAMPLE;
	}

}
