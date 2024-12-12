package oncall.controller;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import oncall.model.Date;
import oncall.model.Rotate;
import oncall.model.StartMonthAndWeek;
import oncall.model.Week;
import oncall.model.Worker;
import oncall.validator.RotateValidator;
import oncall.view.InputView;
import oncall.view.OutputView;

public class WorkController {

    private final InputView inputView;
    private final OutputView outputView;

    public WorkController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {

        List<Date> holiday = initHolidayData();
        StartMonthAndWeek startMonthAndWeek = inputView.inputStartMonthAndWeek();
        startMonthAndWeek.calculateTotalDate();
        Rotate rotate = inputRotate();

        // 배치 결과
        List<Worker> normalWorkers = new ArrayList<>();
        List<Worker> holidayWorkers = new ArrayList<>();

        // 배치 시작
        List<String> normalRotate = rotate.getNormalWorker();
        List<String> holidayRotate = rotate.getHolidayWorker();
        List<String> week = Arrays.stream(Week.values()).map(Week::getText).toList();
        int normalIndex = 0;
        int holidayIndex = 0;
        int weekIndex = week.indexOf(startMonthAndWeek.getStartWeek());

        for (int i = 1; i <= startMonthAndWeek.getTotalDate(); i++) {
            // Date 생성
            Date date = new Date(startMonthAndWeek.getStartMonth(), week.get(weekIndex), i);
            weekIndex = (weekIndex + 1) % week.size();
            boolean isWeekHoliday = date.resolveWeekHoliday();
            boolean isHoliday = date.resolveHoliday(holiday);
            String name = "";
            if (isHoliday || isWeekHoliday) {
                name = holidayRotate.get(holidayIndex);
                holidayIndex = (holidayIndex + 1) % holidayRotate.size();
                holidayWorkers.add(new Worker(name, date, isWeekHoliday, isHoliday));
                continue;
            }
            name = normalRotate.get(normalIndex);
            normalIndex = (normalIndex + 1) % normalRotate.size();
            normalWorkers.add(new Worker(name, date, false, false));
        }
        List<Worker> result = createTotalResult(normalWorkers, holidayWorkers);
        validateResult(normalRotate, holidayRotate, normalWorkers, holidayWorkers, result);
        outputView.printResult(result);
    }

    public void validateResult(List<String> normalRotate,
                               List<String> holidayRotate,
                               List<Worker> normalWorkers,
                               List<Worker> holidayWorkers,
                               List<Worker> result) {

        for (int i = 0; i < result.size() - 1; i++) {
            Worker targetBefore = result.get(i);
            Worker targetAfter = result.get(i + 1);
            if (targetBefore.getName().equals(targetAfter.getName())) { // 2일 연속일 경우
                if (targetAfter.isHoliday() || targetAfter.isWeekHoliday()) {
                    System.out.println("swap 발생");
                    System.out.println(targetAfter.getDate().getDay() + " " + targetAfter.getName());
                    if (i + 1 == result.size()) { // 마지막 일자
                        int next = (holidayRotate.indexOf(targetAfter.getName()) + 1) % holidayRotate.size();
                        String swap = holidayRotate.get(next);
                        targetAfter.setName(swap);
                        continue;
                    }
                    int next = holidayWorkers.indexOf(targetAfter) + 1;
                    targetAfter.swapName(holidayWorkers.get(next));
                    continue;
                }
                if (i + 1 == result.size()) { // 마지막 일자
                    int next = (normalRotate.indexOf(targetAfter.getName()) + 1) % holidayRotate.size();
                    String swap = normalRotate.get(next);
                    targetAfter.setName(swap);
                    continue;
                }
                int next = normalWorkers.indexOf(targetAfter) + 1;
                targetAfter.swapName(normalWorkers.get(next));
            }
        }
    }

    public List<Worker> createTotalResult(List<Worker> normalWorkers, List<Worker> holidayWorkers) {
        List<Worker> result = new ArrayList<>();
        Deque<Worker> normalDeque = new ArrayDeque<>(normalWorkers);
        Deque<Worker> holidayDeque = new ArrayDeque<>(holidayWorkers);
        System.out.println();
        while (!normalDeque.isEmpty() && !holidayDeque.isEmpty()) {
            if (normalDeque.getFirst().getDate().getDay() < holidayDeque.getFirst().getDate().getDay()) {
                result.add(normalDeque.removeFirst());
                continue;
            }
            result.add(holidayDeque.removeFirst());
        }

        if (!normalDeque.isEmpty()) {
            while (!normalDeque.isEmpty()) {
                result.add(normalDeque.removeFirst());
            }
        }
        if (!holidayDeque.isEmpty()) {
            while (!holidayDeque.isEmpty()) {
                result.add(holidayDeque.removeFirst());
            }
        }
        return result;
    }

    public List<Date> initHolidayData() {
        List<Date> holiday = new ArrayList<>();
        holiday.add(new Date(1, 1));
        holiday.add(new Date(3, 1));
        holiday.add(new Date(5, 5));
        holiday.add(new Date(6, 6));
        holiday.add(new Date(8, 15));
        holiday.add(new Date(10, 3));
        holiday.add(new Date(10, 9));
        holiday.add(new Date(12, 25));
        return holiday;
    }

    public Rotate inputRotate() {
        while (true) {
            try {
                List<String> normalWorker = inputView.inputNormalWorker();
                List<String> holidayWorker = inputView.inputHolidayWorker();
                Rotate rotate = new Rotate();
                rotate.addWorker(normalWorker, holidayWorker);
                RotateValidator.validateRotate(normalWorker, holidayWorker);
                return rotate;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }


}
