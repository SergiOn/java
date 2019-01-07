package com.springliquibase.detailservice.client;

import com.springliquibase.detailservice.message.request.GreetingRequest;
import com.springliquibase.detailservice.model.Greeting;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("greeting-service")
public interface GreetingFeignClient {

    @RequestMapping(value = "/hello", consumes = MediaType.APPLICATION_JSON_VALUE)
    List<Greeting> getGreetingByQuery(@RequestParam("name") String name);

    @RequestMapping(value = "/hello/{name}", consumes = MediaType.APPLICATION_JSON_VALUE)
    List<Greeting>  getGreetingByPath(@PathVariable String name);

    @RequestMapping(value = "/hello", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    List<Greeting>  getGreetingByBody(@RequestBody GreetingRequest greetingRequest);

}
