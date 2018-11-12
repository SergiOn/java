package com.packt.sb5be.searchapp.controller;

import com.packt.sb5be.searchapp.dbmodel.orm.Topic;
import com.packt.sb5be.searchapp.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SearchController {

    @Autowired
    TopicRepository topicRepository;

    @RequestMapping("/")
    public List<Topic> index() {
        return topicRepository.findByDescriptionLikeIgnoreCase("%spring%");
    }

}
