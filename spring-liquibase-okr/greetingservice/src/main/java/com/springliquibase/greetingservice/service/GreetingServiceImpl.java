package com.springliquibase.greetingservice.service;

import com.springliquibase.greetingservice.model.Greeting;
import com.springliquibase.greetingservice.repository.GreetingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class GreetingServiceImpl implements GreetingService {

    private final GreetingRepository greetingRepository;

    @Override
    public List<Greeting> getAllGreetings() {
        return greetingRepository.findAll();
    }

    @Override
    public List<Greeting> getAllGreetingsWithName(String name) {
        return getAllGreetings()
                .stream()
                .map(greeting -> new Greeting(
                        greeting.getId(),
                        greeting.getLanguage(),
                        greeting.getTranslation() + " " + name,
                        greeting.getDescription(),
                        greeting.getLanguageType()
                ))
                .collect(Collectors.toList());
    }

}
