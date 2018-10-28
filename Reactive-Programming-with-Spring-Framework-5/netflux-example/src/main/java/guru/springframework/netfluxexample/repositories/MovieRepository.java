package guru.springframework.netfluxexample.repositories;

import guru.springframework.netfluxexample.domain.Movie;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface MovieRepository extends ReactiveMongoRepository<Movie, String> {
}
