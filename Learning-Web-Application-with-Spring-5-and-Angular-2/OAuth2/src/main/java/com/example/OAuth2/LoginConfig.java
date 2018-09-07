package com.example.oauth2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by magemello on 14/05/2017.
 */
@Configuration
@Order(-20)
public class LoginConfig extends WebSecurityConfigurerAdapter{

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.csrf().disable()
                .requestMatchers().antMatchers("/login","/oauth/authorize",
                        "/oauth/confirm_access")
                .and().authorizeRequests().anyRequest().authenticated();
    }

    @Autowired
    public void insertUsers(final AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("mario").password("secret").roles("USER").and()
                .withUser("admin").password("secret").roles("ADMIN");
    }
}
