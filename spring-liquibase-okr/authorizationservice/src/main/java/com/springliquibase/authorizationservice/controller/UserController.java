package com.springliquibase.authorizationservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @RequestMapping(method = { RequestMethod.GET, RequestMethod.POST })
    public ResponseEntity<?> user(OAuth2Authentication user) {
        return ResponseEntity.ok(user);
    }

}
