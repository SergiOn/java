package com.packt.sb5be.searchapp;

//import com.packt.sb5be.searchapp.dbmodel.orm.Topic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Controller {

    @Autowired
    TopicRepository topicRepository;

    @RequestMapping("/")
    public List<Topicc> index() {
        return topicRepository.findByDescriptionLikeIgnoreCase("%spring%");
    }

//    @RequestMapping("/")
//    public String index() {
//        return "Hello World!\n";
//    }
}
