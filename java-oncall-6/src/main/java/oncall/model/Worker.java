package oncall.model;

public class Worker {

    private Date date;
    private boolean isWeekHoliday; // 토요일/일요일 여부
    private boolean isHoliday; // 법정공휴일 여부
    private String name;

    public Worker(String name, Date date, boolean isWeekHoliday, boolean isHoliday) {
        this.name = name;
        this.date = date;
        this.isWeekHoliday = isWeekHoliday;
        this.isHoliday = isHoliday;
    }

    public void swapName(Worker other) {
        String temp = this.name;
        this.name = other.getName();
        other.setName(temp);
    }

    public Date getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    public boolean isWeekHoliday() {
        return isWeekHoliday;
    }

    public boolean isHoliday() {
        return isHoliday;
    }

    public void setName(String name) {
        this.name = name;
    }
}
