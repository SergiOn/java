package com.springliquibase.detailservice.controller;

import com.springliquibase.detailservice.request.GreetingRequest;
import com.springliquibase.detailservice.service.GreetingService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hello")
@AllArgsConstructor
public class GreetingController {

    private final GreetingService greetingService;

    @RequestMapping
    public ResponseEntity<?> helloByQuery(@RequestParam("name") String name) {
        return ResponseEntity.ok(greetingService.getAllGreetingsByQueryWithName(name));
    }

    @RequestMapping("/{name}")
    public ResponseEntity<?> helloByPath(@PathVariable String name) {
        return ResponseEntity.ok(greetingService.getAllGreetingsByPathWithName(name));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> helloByBody(@RequestBody GreetingRequest greetingRequest) {
        return ResponseEntity.ok(greetingService.getAllGreetingsByBodyWithName(greetingRequest));
    }

}
