package com.packt.sb5be.searchapp.repository;

import com.packt.sb5be.searchapp.dbmodel.orm.Topic;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TopicRepository extends CrudRepository<Topic, Long> {
    List<Topic> findByDescriptionLikeIgnoreCase(String description);
}
