package oncall.model;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class Date implements Comparator<Date> {

    int month;
    String week;
    int day;

    public Date(int month, int day) {
        this.month = month;
        this.day = day;
    }

    public Date(int month, String week, int day) {
        this.month = month;
        this.week = week;
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public String getWeek() {
        return week;
    }

    public int getDay() {
        return day;
    }

    public boolean resolveWeekHoliday() {
        return week != null && (week.equals("토") || week.equals("일"));
    }

    public boolean resolveHoliday(List<Date> holiday) {
        return !(week.equals("토") || week.equals("일")) && holiday.contains(this);
    }

    @Override
    public int compare(Date o1, Date o2) {
        return Integer.compare(o1.getDay(), o2.getDay());
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Date date = (Date) o;
        return getMonth() == date.getMonth() && getDay() == date.getDay();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMonth(), getDay());
    }
}
