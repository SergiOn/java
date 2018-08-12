package com.ldeng.service.impl;

import com.ldeng.dao.CommentDao;
import com.ldeng.model.Comment;
import com.ldeng.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDao commentDao;

    @Override
    public Comment save(Comment comment) {
        return commentDao.save(comment);
    }

    @Override
    public List<Comment> findByPhotoId(Long photoId) {
        return commentDao.findByPhotoId(photoId);
    }

    @Override
//    public Comment findOne(Long commentId) {
    public Comment findByCommentId(Long commentId) {
        return commentDao.findByCommentId(commentId);
    }
}
