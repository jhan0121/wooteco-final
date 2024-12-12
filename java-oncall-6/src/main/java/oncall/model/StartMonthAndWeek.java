package oncall.model;

import java.util.Arrays;
import java.util.List;
import oncall.exception.ExceptionType;

public class StartMonthAndWeek {

    int startMonth;
    int totalDate;
    String startWeek;

    public StartMonthAndWeek(int startMonth, String startWeek) {
        this.startMonth = startMonth;
        this.startWeek = startWeek;
    }

    public void calculateTotalDate() {
        List<Month> target = Arrays.stream(Month.values()).filter(i -> i.getMonth() == startMonth).toList();
        if (target.isEmpty()) {
            throw new IllegalArgumentException(ExceptionType.EMPTY_VALUE.getMessage());
        }
        this.totalDate = target.get(0).getTotalDay();
    }

    public int getStartMonth() {
        return startMonth;
    }

    public String getStartWeek() {
        return startWeek;
    }

    public int getTotalDate() {
        return totalDate;
    }
}
