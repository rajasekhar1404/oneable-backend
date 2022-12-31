package ai.oneable.oneable.beans;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "productivityLog")
public class ApplicationLog {
    private String userid;
    private long connectionStartTime;
    private long connectionEndTime;
    private String createdDate;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public long getConnectionStartTime() {
        return connectionStartTime;
    }

    public void setConnectionStartTime(long connectionStartTime) {
        this.connectionStartTime = connectionStartTime;
    }

    public long getConnectionEndTime() {
        return connectionEndTime;
    }

    public void setConnectionEndTime(long connectionEndTime) {
        this.connectionEndTime = connectionEndTime;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }
}