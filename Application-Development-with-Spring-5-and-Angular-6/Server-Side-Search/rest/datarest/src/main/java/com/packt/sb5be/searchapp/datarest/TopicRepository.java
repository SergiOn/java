package com.packt.sb5be.searchapp.datarest;

import com.packt.sb5be.searchapp.datarest.dao.Topic;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface TopicRepository extends PagingAndSortingRepository<Topic, Long> {
    List<Topic> findByDescriptionLikeIgnoreCase(String description);
}
