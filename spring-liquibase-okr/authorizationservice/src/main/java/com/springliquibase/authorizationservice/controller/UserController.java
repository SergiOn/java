package com.springliquibase.authorizationservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @RequestMapping
    public ResponseEntity<?> user(OAuth2Authentication user) {
        return ResponseEntity.ok(user);
    }

}
