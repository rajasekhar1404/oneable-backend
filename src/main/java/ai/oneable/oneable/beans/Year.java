package ai.oneable.oneable.beans;

import java.util.List;

public class Year {
    private String year;
    private List<Month> months;
    private long totalYearDuration;

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public List<Month> getMonths() {
        return months;
    }

    public void setMonths(List<Month> months) {
        this.months = months;
    }

    public long getTotalYearDuration() {
        return totalYearDuration;
    }

    public void setTotalYearDuration(long totalYearDuration) {
        this.totalYearDuration = totalYearDuration;
    }
}
