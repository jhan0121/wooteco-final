package oncall.model;

import java.util.ArrayList;
import java.util.List;

public class Rotate {

    List<String> normalWorker;
    List<String> holidayWorker;

    public Rotate() {
        this.normalWorker = new ArrayList<>();
        this.holidayWorker = new ArrayList<>();
    }

    public void addWorker(List<String> normalWorker, List<String> holidayWorker) {
        this.normalWorker.addAll(normalWorker);
        this.holidayWorker.addAll(holidayWorker);
    }

    public List<String> getNormalWorker() {
        return normalWorker;
    }

    public List<String> getHolidayWorker() {
        return holidayWorker;
    }
}
