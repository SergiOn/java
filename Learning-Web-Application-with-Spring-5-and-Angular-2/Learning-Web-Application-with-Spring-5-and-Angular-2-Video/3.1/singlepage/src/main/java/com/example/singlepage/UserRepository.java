package com.example.singlepage;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by magemello on 14/05/2017.
 */
@RepositoryRestResource(collectionResourceRel = "user", path = "user")
interface UserRepository extends PagingAndSortingRepository<User, Long> {
}
