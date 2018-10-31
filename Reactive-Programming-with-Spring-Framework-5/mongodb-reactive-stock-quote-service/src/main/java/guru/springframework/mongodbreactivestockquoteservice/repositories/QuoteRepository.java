package guru.springframework.mongodbreactivestockquoteservice.repositories;

import guru.springframework.mongodbreactivestockquoteservice.domain.Quote;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface QuoteRepository extends ReactiveMongoRepository<Quote, String> {
}
