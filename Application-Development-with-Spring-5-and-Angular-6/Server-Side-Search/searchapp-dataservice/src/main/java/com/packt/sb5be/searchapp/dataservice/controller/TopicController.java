package com.packt.sb5be.searchapp.dataservice.controller;

import com.packt.sb5be.searchapp.dataservice.dao.TopicCopy;
import com.packt.sb5be.searchapp.dataservice.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class TopicController {

    @Autowired
    TopicRepository topicRepository;

    @GetMapping("/topics")
    public List<TopicCopy> searchTopics(@RequestParam("searchString") String searchString) {
        //return topicRepository.findByDescriptionLikeIgnoreCase("%"+searchString+"%");
        return topicRepository.findByAttributeContainsText("description", searchString);
    }

    @GetMapping("/topic/{id}")
    public Optional<TopicCopy> searchTopics(@PathVariable Long id) {
        return topicRepository.findById(id);
    }

    @PostMapping(value = "/topics")
    public ResponseEntity createTopic(@RequestBody TopicCopy topic) {
        TopicCopy newTopic = topicRepository.save(topic);
        return new ResponseEntity(newTopic, HttpStatus.OK);
    }

    @PatchMapping("/topic/{id}")
    public ResponseEntity patchTopic(@PathVariable Long id, @RequestBody TopicCopy topic) {
        TopicCopy updatedTopic = topicRepository.updateWith(topic,id);
        if (null == updatedTopic) {
            return new ResponseEntity("No topic found for ID " + id, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(updatedTopic, HttpStatus.OK);
    }

    @PutMapping("/topic/{id}")
    public ResponseEntity updateTopic(@PathVariable Long id, @RequestBody TopicCopy topic) {
        Optional<TopicCopy> topicToUpdate = topicRepository.findById(id);
        TopicCopy updatedTopic;
        if (topicToUpdate.isPresent()) {
            // this will call merge instead of persist
            updatedTopic = topicRepository.save(topic);
        }
        else {
            return new ResponseEntity("No topic found for ID " + id, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(updatedTopic, HttpStatus.OK);
    }

    @DeleteMapping("/topic/{id}")
    public ResponseEntity deleteTopic(@PathVariable Long id) {
        Optional<TopicCopy> topic = topicRepository.findById(id);
        if(topic.isPresent()) {
            topicRepository.delete(topic.get());
        } else {
            return new ResponseEntity("No topic found for ID " + id, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(id, HttpStatus.OK);
    }


}
