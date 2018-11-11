package com.packt.sb5be.searchapp;

//import com.packt.sb5be.searchapp.dbmodel.orm.Topic;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TopicRepository extends JpaRepository<Topicc, Long> {

    List<Topicc> findByDescriptionLikeIgnoreCase(String description);

}
