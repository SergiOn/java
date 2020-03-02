package com.ldeng.dao;

import com.ldeng.model.Comment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentDao extends CrudRepository<Comment, Long> {

    Comment save(Comment comment);

//    Comment findOne(Long commentId);
    Comment findByCommentId(Long commentId);

    List<Comment> findByPhotoId(Long photoId);
}
