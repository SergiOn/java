package com.springliquibase.greetingservice.service;

import com.springliquibase.greetingservice.model.Greeting;

import java.util.List;

public interface GreetingService {

    List<Greeting> getAllGreetings();

    List<Greeting> getAllGreetingsWithName(String name);

}
