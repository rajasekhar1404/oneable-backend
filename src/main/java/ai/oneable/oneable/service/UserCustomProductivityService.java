package ai.oneable.oneable.service;

import ai.oneable.oneable.beans.UserProductivityLog;
import ai.oneable.oneable.repository.UserCustomRepository;
import com.mongodb.client.MongoClient;
import com.mongodb.client.AggregateIterable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.stereotype.Component;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import com.mongodb.client.MongoCollection;
import java.util.Arrays;

import java.util.ArrayList;
import java.util.List;

//Implementing the custom repository to perform aggregations
@Component
public class UserCustomProductivityService implements UserCustomRepository {

//    MongoClient getting the database and collection
    @Autowired
    private MongoClient client;

//    MongoConverter is to convert the Document files into Required java class objects.
    @Autowired
    private MongoConverter mongoConverter;

//    It will take the userid and performs the aggregation in the database.
    @Override
    public List<UserProductivityLog> getProductivityByUser(String userid) {

//      Creating the list to collect the results received from database after performing the aggregation
        List<UserProductivityLog> userProductivityLogs = new ArrayList<>();

//      Getting the database
        MongoDatabase database = client.getDatabase("oneable");

//      Getting the collection
        MongoCollection<Document> collection = database.getCollection("productivityLog");

//      Below query generated from MongoDB compass using aggregation pipeline Export to language feature
//      It has 5 stages:
//           1. matching the userid
//           2. Grouping by day and counting the totalDuration of day
//           3. Grouping by month and counting the totalDuration of month
//           4. Grouping by year and counting the totalDuration of year
//           5. Grouping by user and counting the totalDuration of user

        AggregateIterable<Document> result = null;

        if(userid == null) {
             result = collection.aggregate(Arrays.asList(
                    new Document("$group",
                            new Document("_id",
                                    new Document("userid", "$userid")
                                            .append("day",
                                                    new Document("$dateToString",
                                                            new Document("format", "%Y-%m-%d")
                                                                    .append("date",
                                                                            new Document("$toDate", "$createdDate"))))
                                            .append("totalDayDuration",
                                                    new Document("$sum",
                                                            new Document("$subtract", Arrays.asList("$connectionEndTime", "$connectionStartTime"))))
                                            .append("month",
                                                    new Document("$dateToString",
                                                            new Document("format", "%Y-%m")
                                                                    .append("date",
                                                                            new Document("$toDate", "$createdDate"))))
                                            .append("year",
                                                    new Document("$dateToString",
                                                            new Document("format", "%Y")
                                                                    .append("date",
                                                                            new Document("$toDate", "$createdDate")))))),
                    new Document("$group",
                            new Document("_id",
                                    new Document("userid", "$_id.userid")
                                            .append("month", "$_id.month")
                                            .append("year", "$_id.year"))
                                    .append("month",
                                            new Document("$push",
                                                    new Document("day", "$_id.day")
                                                            .append("totalDayDuration", "$_id.totalDayDuration")))
                                    .append("totalMonthDuration",
                                            new Document("$sum", "$_id.totalDayDuration"))),
                    new Document("$group",
                            new Document("_id",
                                    new Document("userid", "$_id.userid")
                                            .append("year", "$_id.year"))
                                    .append("year",
                                            new Document("$push",
                                                    new Document("month", "$_id.month")
                                                            .append("days", "$month")
                                                            .append("totalMonthDuration", "$totalMonthDuration")))
                                    .append("totalYearDuration",
                                            new Document("$sum", "$totalMonthDuration"))),
                    new Document("$group",
                            new Document("_id", "$_id.userid")
                                    .append("user",
                                            new Document("$push",
                                                    new Document("year", "$_id.year")
                                                            .append("months", "$year")
                                                            .append("totalYearDuration", "$totalYearDuration")))
                                    .append("totalUserDuration",
                                            new Document("$sum", "$totalYearDuration")))));
        } else {
            result = collection.aggregate(Arrays.asList(
                    new Document("$match",
                            new Document("userid", userid)),
                    new Document("$group",
                            new Document("_id",
                                    new Document("userid", "$userid")
                                            .append("day",
                                                    new Document("$dateToString",
                                                            new Document("format", "%Y-%m-%d")
                                                                    .append("date",
                                                                            new Document("$toDate", "$createdDate"))))
                                            .append("totalDayDuration",
                                                    new Document("$sum",
                                                            new Document("$subtract", Arrays.asList("$connectionEndTime", "$connectionStartTime"))))
                                            .append("month",
                                                    new Document("$dateToString",
                                                            new Document("format", "%Y-%m")
                                                                    .append("date",
                                                                            new Document("$toDate", "$createdDate"))))
                                            .append("year",
                                                    new Document("$dateToString",
                                                            new Document("format", "%Y")
                                                                    .append("date",
                                                                            new Document("$toDate", "$createdDate")))))),
                    new Document("$group",
                            new Document("_id",
                                    new Document("userid", "$_id.userid")
                                            .append("month", "$_id.month")
                                            .append("year", "$_id.year"))
                                    .append("month",
                                            new Document("$push",
                                                    new Document("day", "$_id.day")
                                                            .append("totalDayDuration", "$_id.totalDayDuration")))
                                    .append("totalMonthDuration",
                                            new Document("$sum", "$_id.totalDayDuration"))),
                    new Document("$group",
                            new Document("_id",
                                    new Document("userid", "$_id.userid")
                                            .append("year", "$_id.year"))
                                    .append("year",
                                            new Document("$push",
                                                    new Document("month", "$_id.month")
                                                            .append("days", "$month")
                                                            .append("totalMonthDuration", "$totalMonthDuration")))
                                    .append("totalYearDuration",
                                            new Document("$sum", "$totalMonthDuration"))),
                    new Document("$group",
                            new Document("_id", "$_id.userid")
                                    .append("user",
                                            new Document("$push",
                                                    new Document("year", "$_id.year")
                                                            .append("months", "$year")
                                                            .append("totalYearDuration", "$totalYearDuration")))
                                    .append("totalUserDuration",
                                            new Document("$sum", "$totalYearDuration")))));
        }



//      Looping the the result and converting each document to UserProdeuctivityLog class then adding it to the list of userProductivityLogs
        result.forEach(doc -> userProductivityLogs.add(mongoConverter.read(UserProductivityLog.class, doc)));

        return userProductivityLogs;
    }
}