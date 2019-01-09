package com.springliquibase.authorizationservice.controller;

import com.springliquibase.authorizationservice.model.User;
import com.springliquibase.authorizationservice.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping({"/registration", "/sign-up"})
@AllArgsConstructor
public class RegistrationController {

    private UserService userService;

    @RequestMapping(method = { RequestMethod.POST, RequestMethod.PUT })
    public ResponseEntity<?> login(@Valid @RequestBody User user) {
        boolean isUserNotExist = userService.isUserNotExist(user);

        if (isUserNotExist) {
            User userSaved = userService.saveUser(user);
            return ResponseEntity.ok(userSaved);
        } else {
            return ResponseEntity.badRequest().body("User with username or email already exists. Nothing will be done.");
        }

//        return Stream.of(isUserNotExist)
//                .map(value -> {
//                    if (value) {
//                        User userSaved = userService.saveUser(user);
//                        return ResponseEntity.ok(userSaved);
//                    } else {
//                        return ResponseEntity.badRequest().body("User with username or email already exists. Nothing will be done.");
//                    }
//                })
//                .findFirst()
//                .get();
    }

}
