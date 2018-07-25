package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.awt.*;

@RestController
@RequestMapping("/stream")
public class StreamController {

    @Autowired
    StreamService streamService;

    @GetMapping(value = "/{id}")
    public Mono<Friend> byId(@PathVariable Integer id){
        return streamService.byId(id);
    }

    @GetMapping(value="/all")
    public Flux<Friend> all(){
        return streamService.all();
    }

    @GetMapping(value="/events", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Friend> events(){
        return streamService.events();
    }
}
