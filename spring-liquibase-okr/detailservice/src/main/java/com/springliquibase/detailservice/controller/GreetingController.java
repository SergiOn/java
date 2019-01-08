package com.springliquibase.detailservice.controller;

import com.springliquibase.detailservice.message.request.GreetingRequest;
import com.springliquibase.detailservice.service.GreetingService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/hello")
@AllArgsConstructor
@Slf4j
public class GreetingController {

    private final GreetingService greetingService;

    @RequestMapping
    public ResponseEntity<?> helloByQuery(@RequestParam("name") String name) {
        log.debug("GreetingController -> helloByQuery");
        return ResponseEntity.ok(greetingService.getAllGreetingsByQueryWithName(name));
    }

    @RequestMapping("/{name}")
    public ResponseEntity<?> helloByPath(@PathVariable String name) {
        log.debug("GreetingController -> helloByPath");
        return ResponseEntity.ok(greetingService.getAllGreetingsByPathWithName(name));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> helloByBody(@Valid @RequestBody GreetingRequest greetingRequest) {
        log.debug("GreetingController -> helloByBody");
        return ResponseEntity.ok(greetingService.getAllGreetingsByBodyWithName(greetingRequest));
    }

}
