package ai.oneable.oneable.beans;

import java.util.List;

public class Month {
    private String month;
    private List<Day> days;
    private long totalMonthDuration;

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public List<Day> getDays() {
        return days;
    }

    public void setDays(List<Day> days) {
        this.days = days;
    }

    public long getTotalMonthDuration() {
        return totalMonthDuration;
    }

    public void setTotalMonthDuration(long totalMonthDuration) {
        this.totalMonthDuration = totalMonthDuration;
    }
}