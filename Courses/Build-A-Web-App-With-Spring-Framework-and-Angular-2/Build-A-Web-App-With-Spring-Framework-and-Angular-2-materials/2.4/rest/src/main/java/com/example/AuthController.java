package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    TokenService tokenService;

    @GetMapping("private")
    public ResponseEntity<?> privateEndPoint(@RequestParam(required = false, value="token") String token){
        if(tokenService.isValid(token)){
            return new ResponseEntity<>("I'm a private endpoint", HttpStatus.OK);
        }

        return new ResponseEntity<>("Unauthorized", HttpStatus.UNAUTHORIZED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user){
        if(userRepository.isAuthorized(user)){
            return new ResponseEntity<>(tokenService.create(user), HttpStatus.OK);
        }

        return new ResponseEntity<>("Unauthorized", HttpStatus.UNAUTHORIZED);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestParam(required = false, value="token") String token){
        tokenService.delete(token);
        return new ResponseEntity<>("delete", HttpStatus.OK);
    }
}
