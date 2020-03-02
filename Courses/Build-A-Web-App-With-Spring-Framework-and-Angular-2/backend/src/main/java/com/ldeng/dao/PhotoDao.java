package com.ldeng.dao;

import com.ldeng.model.Photo;
import com.ldeng.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhotoDao extends CrudRepository<Photo, Long> {
    Photo save(Photo photo);

    List<Photo> findByUser(User user);

    List<Photo> findAll();

    Photo findByPhotoId(Long photoId);
}
