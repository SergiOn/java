package com.springliquibase.authorizationservice.repository;

import com.springliquibase.authorizationservice.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

//    User findByUsername(String username);
    Optional<User> findByUsername(String username);
    List<User> findByUsernameOrEmail(String username, String email);

}
