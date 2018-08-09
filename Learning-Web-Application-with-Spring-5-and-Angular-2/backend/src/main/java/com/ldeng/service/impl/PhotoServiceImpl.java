package com.ldeng.service.impl;

import com.ldeng.dao.PhotoDao;
import com.ldeng.model.Photo;
import com.ldeng.model.User;
import com.ldeng.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhotoServiceImpl implements PhotoService {

    @Autowired
    private PhotoDao photoDao;

    @Override
    public Photo save(Photo photo) {
        return photoDao.save(photo);
    }

    @Override
    public List<Photo> findAll() {
        return photoDao.findAll();
    }

    @Override
    public List<Photo> findByUser(User user) {
        return photoDao.findByUser(user);
    }

    @Override
    public Photo findByPhotoId(Long photoId) {
        return photoDao.findByPhotoId(photoId);
    }
}
