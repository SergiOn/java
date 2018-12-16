package itemsharing.authorizationserver.repository;

import itemsharing.authorizationserver.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);
//    Optional<User> findByUsername(String username);

}
