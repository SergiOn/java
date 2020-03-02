package com.example.singlepage;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "user", path = "user")
interface UserRepository extends PagingAndSortingRepository<User, Long> {
}
