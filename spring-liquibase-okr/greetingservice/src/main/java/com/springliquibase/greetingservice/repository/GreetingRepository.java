package com.springliquibase.greetingservice.repository;

import com.springliquibase.greetingservice.model.Greeting;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GreetingRepository extends CrudRepository<Greeting, Long> {

    @Override
    List<Greeting> findAll();

}
