package com.packt.sb5be.searchapp.service;

import com.packt.sb5be.searchapp.service.dao.Topic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class DataService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    WebClient webClient = WebClient.create("http://localhost:8080");


    public Mono<Topic> getTopic(Long id) {

        return webClient.get()
                .uri("/topics/{id}", id).accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Topic.class);
    }


}
