package com.springliquibase.greetingservice.message.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;


@Getter
@Setter
public class GreetingRequest {

    @NotEmpty
    private String name;

}
