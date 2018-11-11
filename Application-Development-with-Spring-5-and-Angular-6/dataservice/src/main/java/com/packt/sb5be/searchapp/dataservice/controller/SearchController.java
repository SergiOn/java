package com.packt.sb5be.searchapp.dataservice.controller;

import com.packt.sb5be.searchapp.dataservice.dbmodel.orm.Topic;
import com.packt.sb5be.searchapp.dataservice.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SearchController {

    @Autowired
    TopicRepository topicRepository;

    @GetMapping("/topics")
    public List<Topic> searchTopics(@RequestParam("searchString") String searchString) {
        return topicRepository.findByDescriptionLikeIgnoreCase("%"+searchString+"%");
    }
}
