package com.itemsharing.userservice.repository;

import com.itemsharing.userservice.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);
//    Optional<User> findByUsername(String username);

}
