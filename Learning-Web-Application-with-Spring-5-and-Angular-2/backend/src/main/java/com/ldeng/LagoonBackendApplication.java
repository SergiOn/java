package com.ldeng;

import com.ldeng.config.JwtFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class LagoonBackendApplication {

	@Bean
	public FilterRegistrationBean<JwtFilter> jwtFilter() {

		final FilterRegistrationBean<JwtFilter> registrationBean = new FilterRegistrationBean<>();
		registrationBean.setFilter(new JwtFilter());
		registrationBean.addUrlPatterns("/rest/*");
		return registrationBean;
	}

	public static void main(String[] args) {
		SpringApplication.run(LagoonBackendApplication.class, args);
	}
}
