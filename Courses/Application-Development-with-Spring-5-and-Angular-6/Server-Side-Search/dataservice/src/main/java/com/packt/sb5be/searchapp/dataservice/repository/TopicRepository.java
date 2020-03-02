package com.packt.sb5be.searchapp.dataservice.repository;

import com.packt.sb5be.searchapp.dataservice.dao.TopicCopy;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TopicRepository extends CrudRepository<TopicCopy, Long>, ExtendedRepository<TopicCopy, Long> {
    List<TopicCopy> findByDescriptionLikeIgnoreCase(String description);
}
