package com.ldeng.service;

import com.ldeng.model.Comment;

import java.util.List;

public interface CommentService {
    Comment save(Comment comment);

    List<Comment> findByPhotoId(Long photoId);

    Comment findOne(Long commentId);
}
