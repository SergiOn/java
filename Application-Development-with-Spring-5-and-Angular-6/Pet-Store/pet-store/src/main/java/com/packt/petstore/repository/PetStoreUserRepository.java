package com.packt.petstore.repository;

import com.packt.petstore.model.PetStoreUser;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface PetStoreUserRepository extends ReactiveCrudRepository<PetStoreUser, String> {
}
