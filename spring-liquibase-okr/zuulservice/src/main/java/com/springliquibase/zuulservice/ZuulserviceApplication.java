package com.springliquibase.zuulservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication
@EnableZuulProxy
@EnableEurekaClient
@EnableResourceServer
public class ZuulserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZuulserviceApplication.class, args);
	}

}
