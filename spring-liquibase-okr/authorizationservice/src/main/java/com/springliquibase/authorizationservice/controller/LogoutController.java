package com.springliquibase.authorizationservice.controller;

import com.springliquibase.authorizationservice.message.request.LogoutRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
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

    private final TokenStore tokenStore;
    private final DefaultTokenServices tokenServices;

    @RequestMapping(method = { RequestMethod.DELETE, RequestMethod.POST })
    public ResponseEntity<?> logoutByBody(@Valid @RequestBody LogoutRequest logoutRequest) {
        String token = logoutRequest.getToken();

        log.debug("Token value: {}", token);

        OAuth2AccessToken accessToken = tokenStore.readAccessToken(token);

        log.debug("AccessToken value: {}", accessToken);

        tokenStore.removeAccessToken(accessToken);
        tokenServices.revokeToken(token);

        return ResponseEntity.ok("The token was removed.");
    }

}

/*
The default implementation of a JWT is stateless.
That means you don't hold any information regarding the individual token
in any form of storage (files, databases, memory, etc.).
You're relying on the signature of the JWT to validate that you have issued this token.
*/
