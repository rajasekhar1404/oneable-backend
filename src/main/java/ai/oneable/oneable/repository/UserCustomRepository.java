package ai.oneable.oneable.repository;

import ai.oneable.oneable.beans.UserProductivityLog;

import java.util.List;

public interface UserCustomRepository {

//  For getting the Aggregated productivity of user sent as params
    List<UserProductivityLog> getProductivityByUser(String userid);
}