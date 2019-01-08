package com.springliquibase.authorizationservice;

import com.springliquibase.authorizationservice.model.User;
import com.springliquibase.authorizationservice.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication
@EnableAuthorizationServer
@EnableEurekaClient
@EnableResourceServer
@AllArgsConstructor
public class AuthorizationserviceApplication implements CommandLineRunner {

	private UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(AuthorizationserviceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		User user1 = new User();
		user1.setFirstName("Serhii");
		user1.setLastName("On");
		user1.setUsername("SerhiiOn");
		user1.setPassword("12345");
		user1.setEmail("email@mail.com");

		userService.createDefaultUser(user1);

	}
}
