package com.ldeng.service;

import com.ldeng.model.Photo;
import com.ldeng.model.User;

import java.util.List;

public interface PhotoService {
    Photo save(Photo photo);

    List<Photo> findAll();

    List<Photo> findByUser(User user);

    Photo findByPhotoId(Long photoId);
}
