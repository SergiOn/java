package com.springliquibase.authorizationservice.controller;

import com.springliquibase.authorizationservice.message.request.LogoutRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping({"/logout", "/sign-out"})
@AllArgsConstructor
@Slf4j
public class LogoutController {

    private TokenStore tokenStore;
    private DefaultTokenServices defaultTokenServices;
    private ConsumerTokenServices consumerTokenServices;
    private JwtTokenStore jwtTokenStore;

    @RequestMapping(value = "/param", method = { RequestMethod.DELETE, RequestMethod.POST })
    public ResponseEntity<?> logoutByParam(@RequestParam("token") String token) {
        OAuth2AccessToken accessToken = tokenStore.readAccessToken(token);
        tokenStore.removeAccessToken(accessToken);

        return ResponseEntity.ok("The token was removed.");
    }

    @RequestMapping(value = "/path/{token}", method = { RequestMethod.DELETE, RequestMethod.POST })
    public ResponseEntity<?> logoutByPath(@PathVariable String token) {
        OAuth2AccessToken accessToken = tokenStore.readAccessToken(token);
        tokenStore.removeAccessToken(accessToken);
        return ResponseEntity.ok("The token was removed.");
    }

    @RequestMapping(method = { RequestMethod.DELETE, RequestMethod.POST })
    public ResponseEntity<?> logoutByBody(@Valid @RequestBody LogoutRequest logoutRequest) {
        String token = logoutRequest.getToken();

        log.debug("Token value: {}", token);

        OAuth2AccessToken accessToken = tokenStore.readAccessToken(token);
        tokenStore.removeAccessToken(accessToken);

        log.debug("AccessToken value: {}", accessToken);

        boolean result = defaultTokenServices.revokeToken(token);
        boolean result2 = consumerTokenServices.revokeToken(token);
        jwtTokenStore.removeAccessToken(accessToken);

        log.debug("Delete token result: {}", result);
        log.debug("Delete token result2: {}", result2);

        return ResponseEntity.ok("The token was removed.");
    }

}
