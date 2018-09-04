package com.example.oauth2;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * Created by magemello on 14/05/2017.
 */

@RestController
@RequestMapping("/private")
public class UserRestcontroller {

    @GetMapping("/user")
    public Principal user(Principal user){
        return user;
    }
}
