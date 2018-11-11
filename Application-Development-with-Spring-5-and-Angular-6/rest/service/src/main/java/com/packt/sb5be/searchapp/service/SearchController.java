package com.packt.sb5be.searchapp.service;

import com.packt.sb5be.searchapp.service.dao.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class SearchController {

    @Autowired
    DataService dataService;

    @GetMapping("/topic/{id}")
    public Mono<Topic> getTopic(@PathVariable Long id) {
        return dataService.getTopic(id);
    }

}
