package ai.oneable.oneable.service;

import ai.oneable.oneable.beans.ApplicationLog;
import ai.oneable.oneable.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserProductivityLogService {

    @Autowired
    private UserRepository userRepository;

//    will add the applications log in to the database
    public ApplicationLog addLog(ApplicationLog applicationLog) {
        return userRepository.save(applicationLog);
    }

}