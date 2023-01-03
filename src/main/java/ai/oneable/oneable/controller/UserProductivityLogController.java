package ai.oneable.oneable.controller;

import ai.oneable.oneable.beans.ApplicationLog;
import ai.oneable.oneable.beans.UserProductivityLog;
import ai.oneable.oneable.service.UserCustomProductivityService;
import ai.oneable.oneable.service.UserProductivityLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserProductivityLogController {

    @Autowired
    private UserProductivityLogService userProductivityLogService;

    @Autowired
    private UserCustomProductivityService userCustomProductivityService;

    Logger logger = LoggerFactory.getLogger(UserProductivityLogController.class);

//  It will get the productivity of user passed to it
    @GetMapping("/productivity/{userid}")
    public List<UserProductivityLog> getProductivityByUser(@PathVariable("userid") String userid) {
        logger.trace("method happened");
        return userCustomProductivityService.getProductivityByUser(userid);
    }

//  It will add the application logs in to the database
    @PutMapping("/applicationlog")
    public ApplicationLog addUserData(@RequestBody ApplicationLog applicationLog) {
        return userProductivityLogService.addLog(applicationLog);
    }

    @GetMapping("/productivity")
    public List<UserProductivityLog> getTotalProductivity() {
        return getProductivityByUser(null);
    }

}