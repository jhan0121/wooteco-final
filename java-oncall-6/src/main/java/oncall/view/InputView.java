package oncall.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.List;
import oncall.model.StartMonthAndWeek;
import oncall.util.Seperator;
import oncall.validator.InputValidator;

public class InputView {

    private String readLine() {
        return Console.readLine();
    }

    public StartMonthAndWeek inputStartMonthAndWeek() {
        while (true) {
            try {
                System.out.print(InputMessage.MONTH_AND_DAY.getText());
                String input = readLine();
                InputValidator.validateMonthAndWeekInput(input);
                List<String> split = Seperator.split(input);
                InputValidator.validateSplitSize(split, 2);
                StartMonthAndWeek startMonthAndWeek = new StartMonthAndWeek(
                        Integer.parseInt(split.get(0)),
                        split.get(1));
                InputValidator.validateMonth(startMonthAndWeek.getStartMonth());
                InputValidator.validateWeek(startMonthAndWeek.getStartWeek());
                return startMonthAndWeek;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public List<String> inputNormalWorker() {
        while (true) {
            try {
                System.out.print(InputMessage.NORMAL_WORKER.getText());
                String input = readLine();
                InputValidator.validateWorker(input);
                List<String> split = Seperator.split(input);
                InputValidator.validateWorker(split);
                return split;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public List<String> inputHolidayWorker() {
        System.out.print(InputMessage.HOLIDAY_WORKER.getText());
        String input = readLine();
        InputValidator.validateWorker(input);
        List<String> split = Seperator.split(input);
        InputValidator.validateWorker(split);
        return split;
    }
}
