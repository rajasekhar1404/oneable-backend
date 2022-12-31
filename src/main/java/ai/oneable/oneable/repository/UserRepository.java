package ai.oneable.oneable.repository;

import ai.oneable.oneable.beans.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

}