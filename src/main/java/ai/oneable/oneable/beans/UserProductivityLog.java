package ai.oneable.oneable.beans;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "productivityLog")
public class UserProductivityLog {
    @Id
    private String userid;
    private List<Year> user;
    private long totalUserDuration;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public List<Year> getUser() {
        return user;
    }

    public void setUser(List<Year> user) {
        this.user = user;
    }

    public long getTotalUserDuration() {
        return totalUserDuration;
    }

    public void setTotalUserDuration(long totalUserDuration) {
        this.totalUserDuration = totalUserDuration;
    }
}