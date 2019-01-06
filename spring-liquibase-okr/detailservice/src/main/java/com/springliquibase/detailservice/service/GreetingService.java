package com.springliquibase.detailservice.service;

import com.springliquibase.detailservice.model.Greeting;
import com.springliquibase.detailservice.message.request.GreetingRequest;

import java.util.List;

public interface GreetingService {

    List<Greeting> getAllGreetingsByQueryWithName(String name);
    List<Greeting> getAllGreetingsByPathWithName(String name);
    List<Greeting> getAllGreetingsByBodyWithName(GreetingRequest greetingRequest);

}
