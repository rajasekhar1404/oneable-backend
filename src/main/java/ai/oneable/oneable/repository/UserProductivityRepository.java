package ai.oneable.oneable.repository;

import ai.oneable.oneable.beans.ApplicationLog;
import org.springframework.data.mongodb.repository.MongoRepository;

/*
    Extending the MongoRepository to access the inbuilt methods like findAll, findById.. etc.
 */
public interface UserProductivityRepository extends MongoRepository<ApplicationLog, String> {

}