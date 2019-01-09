package com.springliquibase.authorizationservice.message.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class LogoutRequest {

    @NotEmpty
    private String token;

}
