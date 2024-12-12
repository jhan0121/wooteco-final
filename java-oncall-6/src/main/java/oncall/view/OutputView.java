package oncall.view;

import java.util.List;
import oncall.model.Worker;

public class OutputView {

    private static final String LINE_SEPARATOR = System.lineSeparator();

    public void printResult(List<Worker> result) {
        System.out.println();
        for (Worker worker : result) {
            if (worker.isHoliday() && !worker.isWeekHoliday()) {
                System.out.printf("%d월 %d일 %s(휴일) %s" + LINE_SEPARATOR,
                        worker.getDate().getMonth(),
                        worker.getDate().getDay(),
                        worker.getDate().getWeek(),
                        worker.getName());
                continue;
            }
            System.out.printf("%d월 %d일 %s %s" + LINE_SEPARATOR,
                    worker.getDate().getMonth(),
                    worker.getDate().getDay(),
                    worker.getDate().getWeek(),
                    worker.getName());
        }
    }
}
