package ai.oneable.oneable.service;

import ai.oneable.oneable.beans.ApplicationLog;
import ai.oneable.oneable.repository.UserProductivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserProductivityLogService {

    @Autowired
    private UserProductivityRepository userProductivityRepository;

//    will add the applications log in to the database
    public ApplicationLog addLog(ApplicationLog applicationLog) {
        return userProductivityRepository.save(applicationLog);
    }

}