package com.springliquibase.zuulservice.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
public class SecurityConfig extends ResourceServerConfigurerAdapter {

    private static final String[] PUBLIC_MATCHERS = {
            "/",
            "/api/detail/**",
            "/api/auth/**"
    };

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.logout().disable()
            .authorizeRequests()
                .antMatchers(PUBLIC_MATCHERS)
                .permitAll()
                .anyRequest()
                .authenticated();
    }

}
