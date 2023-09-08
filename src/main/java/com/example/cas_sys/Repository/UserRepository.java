package com.example.cas_sys.Repository;

import com.example.cas_sys.Model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

/**
 * Repository for the {@link User} JPA entity. Any custom methods, not
 * already defined in {@link MongoRepository}, are to be defined here
 */
public interface UserRepository extends MongoRepository<User, String> {

	@Query(value = "{'username' : ?0 }", count = true)
	int findByUsernameCount(String username);

	@Query(value = "{'username' : ?0 }")
	Optional<User> findByUsername(String username);

	@Query(value = "{'email' : ?0 }")
	Optional<User> findByEmail(String email);

}
