package com.springliquibase.greetingservice.controller;

import com.springliquibase.greetingservice.message.request.GreetingRequest;
import com.springliquibase.greetingservice.service.GreetingService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/hello")
@AllArgsConstructor
public class GreetingController {

    private final GreetingService greetingService;

    @RequestMapping
    public ResponseEntity<?> helloByQuery(@RequestParam("name") String name) {
        return ResponseEntity.ok(greetingService.getAllGreetingsWithName(name));
    }

    @RequestMapping("/{name}")
    public ResponseEntity<?> helloByPath(@PathVariable String name) {
        return ResponseEntity.ok(greetingService.getAllGreetingsWithName(name));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> helloByBody(@Valid @RequestBody GreetingRequest greetingRequest) {
        return ResponseEntity.ok(greetingService.getAllGreetingsWithName(greetingRequest.getName()));
    }

}
