package com.springliquibase.authorizationservice.controller;

import com.springliquibase.authorizationservice.message.request.LogoutRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping({"/logout", "/sign-out"})
@AllArgsConstructor
@Slf4j
public class LogoutController {

//    @RequestMapping(value = "/param", method = { RequestMethod.DELETE, RequestMethod.POST })
//    public ResponseEntity<?> logoutByParam(@RequestParam("token") String token) {
//        OAuth2AccessToken accessToken = tokenStore.readAccessToken(token);
//        tokenStore.removeAccessToken(accessToken);
//
//        return ResponseEntity.ok("The token was removed.");
//    }
//
//    @RequestMapping(value = "/path/{token}", method = { RequestMethod.DELETE, RequestMethod.POST })
//    public ResponseEntity<?> logoutByPath(@PathVariable String token) {
//        OAuth2AccessToken accessToken = tokenStore.readAccessToken(token);
//        tokenStore.removeAccessToken(accessToken);
//        return ResponseEntity.ok("The token was removed.");
//    }

    @RequestMapping(method = { RequestMethod.DELETE, RequestMethod.POST })
    public ResponseEntity<?> logoutByBody(@Valid @RequestBody LogoutRequest logoutRequest) {
        String token = logoutRequest.getToken();

        log.debug("Token value: {}", token);

//        tokenConverter.extractAccessToken(token, new HashMap<>());

//        OAuth2RefreshToken accessToken = tokenStore.readRefreshToken(token);
//        OAuth2RefreshToken accessToken2 = jwtTokenStore.readRefreshToken(token);
//
//        tokenStore.removeRefreshToken(accessToken);
//        jwtTokenStore.removeRefreshToken(accessToken);
//
//        log.debug("AccessToken value: {}", accessToken);
//        log.debug("AccessToken2 value: {}", accessToken2);

//        boolean result = defaultTokenServices.revokeToken(token);
//        boolean result2 = consumerTokenServices.revokeToken(token);
//        jwtTokenStore.removeAccessToken(accessToken);

//        log.debug("Delete token result: {}", result);
//        log.debug("Delete token result2: {}", result2);
        return ResponseEntity.ok("The token was removed.");
    }

}

/*
The default implementation of a JWT is stateless.
That means you don't hold any information regarding the individual token
in any form of storage (files, databases, memory, etc.).
You're relying on the signature of the JWT to validate that you have issued this token.
*/
