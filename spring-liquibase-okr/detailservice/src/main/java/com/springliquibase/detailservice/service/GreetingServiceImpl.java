package com.springliquibase.detailservice.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.springliquibase.detailservice.client.GreetingFeignClient;
import com.springliquibase.detailservice.message.request.GreetingRequest;
import com.springliquibase.detailservice.model.Greeting;
import com.springliquibase.detailservice.model.LanguageType;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class GreetingServiceImpl implements GreetingService {

    private final GreetingFeignClient greetingFeignClient;

    // https://stackoverflow.com/questions/51137893/feign-client-concurrency-issue

    @Override
    @HystrixCommand(fallbackMethod = "getAllGreetingsFallback", commandProperties = {
            @HystrixProperty(name = "execution.isolation.strategy", value = "SEMAPHORE")
    })
    public List<Greeting> getAllGreetingsByQueryWithName(String name) {
        log.debug("GreetingServiceImpl -> getAllGreetingsByQueryWithName");
        return greetingFeignClient.getGreetingByQuery(name);
    }

    @Override
    @HystrixCommand(fallbackMethod = "getAllGreetingsFallback", commandProperties = {
            @HystrixProperty(name = "execution.isolation.strategy", value = "SEMAPHORE")
    })
    public List<Greeting> getAllGreetingsByPathWithName(String name) {
        log.debug("GreetingServiceImpl -> getAllGreetingsByPathWithName");
        return greetingFeignClient.getGreetingByPath(name);
    }

    @Override
    @HystrixCommand(fallbackMethod = "getAllGreetingsByBodyFallback", commandProperties = {
            @HystrixProperty(name = "execution.isolation.strategy", value = "SEMAPHORE")
    })
    public List<Greeting> getAllGreetingsByBodyWithName(GreetingRequest greetingRequest) {
        log.debug("GreetingServiceImpl -> getAllGreetingsByBodyWithName");
        return greetingFeignClient.getGreetingByBody(greetingRequest);
    }

    private List<Greeting> getAllGreetingsFallback(String name) {
        log.debug("GreetingServiceImpl -> getAllGreetingsFallback");
        LanguageType languageType = new LanguageType(100L, "LanguageType type", "LanguageType description");
        Greeting greeting = new Greeting(1L, "Greeting language", "Greeting translation: Hello" + " " + name, "Greeting description", languageType);
        return Arrays.asList(greeting);
    }

    private List<Greeting> getAllGreetingsByBodyFallback(GreetingRequest greetingRequest) {
        log.debug("GreetingServiceImpl -> getAllGreetingsByBodyFallback");
        return getAllGreetingsFallback(greetingRequest.getName());
    }

}
