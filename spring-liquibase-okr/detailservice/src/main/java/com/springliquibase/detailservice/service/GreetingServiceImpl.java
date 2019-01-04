package com.springliquibase.detailservice.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.springliquibase.detailservice.client.GreetingFeignClient;
import com.springliquibase.detailservice.model.Greeting;
import com.springliquibase.detailservice.model.LanguageType;
import com.springliquibase.detailservice.request.GreetingRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@AllArgsConstructor
public class GreetingServiceImpl implements GreetingService {

    private final GreetingFeignClient greetingFeignClient;

    @Override
    @HystrixCommand(fallbackMethod = "getAllGreetingsFallback")
    public List<Greeting> getAllGreetingsByQueryWithName(String name) {
        return greetingFeignClient.getGreetingByQuery(name);
    }

    @Override
    @HystrixCommand(fallbackMethod = "getAllGreetingsFallback")
    public List<Greeting> getAllGreetingsByPathWithName(String name) {
        return greetingFeignClient.getGreetingByPath(name);
    }

    @Override
    @HystrixCommand(fallbackMethod = "getAllGreetingsByBodyFallback")
    public List<Greeting> getAllGreetingsByBodyWithName(GreetingRequest greetingRequest) {
        return greetingFeignClient.getGreetingByBody(greetingRequest);
    }

    private List<Greeting> getAllGreetingsFallback(String name) {
        LanguageType languageType = new LanguageType(100L, "LanguageType type", "LanguageType description");
        Greeting greeting = new Greeting(1L, "Greeting language", "Greeting translation: Hello" + " " + name, "Greeting description", languageType);
        return Arrays.asList(greeting);
    }

    private List<Greeting> getAllGreetingsByBodyFallback(GreetingRequest greetingRequest) {
        return getAllGreetingsFallback(greetingRequest.getName());
    }

}
