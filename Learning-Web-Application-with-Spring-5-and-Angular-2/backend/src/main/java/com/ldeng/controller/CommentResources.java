package com.ldeng.controller;

import com.ldeng.model.Comment;
import com.ldeng.model.Photo;
import com.ldeng.service.CommentService;
import com.ldeng.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest")
public class CommentResources {

    @Autowired
    private PhotoService photoService;

    @Autowired
    private CommentService commentService;

    @RequestMapping(value = "/comment/add", method = RequestMethod.POST)
    public void addComment(@RequestBody Comment comment) {
        Photo photo = photoService.findByPhotoId(comment.getPhotoId());
        List<Comment> commentList = photo.getCommentList();
        commentService.save(comment);
    }

}
