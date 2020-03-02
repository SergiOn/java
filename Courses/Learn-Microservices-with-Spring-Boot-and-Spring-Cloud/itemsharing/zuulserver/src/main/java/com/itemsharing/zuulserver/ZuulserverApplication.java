package com.itemsharing.zuulserver;

import brave.sampler.Sampler;
import com.itemsharing.zuulserver.util.UserContextInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@SpringBootApplication
@EnableZuulProxy
public class ZuulserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZuulserverApplication.class, args);
	}

	@Bean
	public RestTemplate getRestTemplate() {
		RestTemplate template = new RestTemplate();
		List interceptors = template.getInterceptors();

		if(interceptors == null) {
			template.setInterceptors(Collections.singletonList(new UserContextInterceptor()));
		} else {
			interceptors.add(new UserContextInterceptor());
			template.setInterceptors(interceptors);
		}

		return template;
	}

	//spring.sleuth.sampler.percentage .5
	//spring.sleuth.sampler.probability .5  // new
	@Bean
	public Sampler defaultSampler() {
//		return new AlwaysSampler();
		return Sampler.ALWAYS_SAMPLE;
	}

}

