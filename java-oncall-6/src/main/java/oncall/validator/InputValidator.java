package oncall.validator;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import oncall.exception.ExceptionType;

public class InputValidator {

    private static final String MONTH_AND_WEEK_REGEX = "^[가-힣0-9, ]+$";
    private static final String START_WEEK_REGEX = "^[월화수목금토일]+$";
    private static final String WORKER_REGEX = "^[가-힣A-Za-z, ]+$";

    private InputValidator() {

    }

    public static void validateMonthAndWeekInput(String input) {
        if (!input.matches(MONTH_AND_WEEK_REGEX)) {
            throw new IllegalArgumentException(ExceptionType.INVALID_INPUT.getMessage());
        }
    }

    public static void validateSplitSize(List<String> split, int size) {
        if (split.size() != size) {
            throw new IllegalArgumentException(ExceptionType.INVALID_INPUT.getMessage());
        }
    }

    public static void validateWorker(String input) {
        if (!input.matches(WORKER_REGEX)) {
            throw new IllegalArgumentException(ExceptionType.INVALID_INPUT.getMessage());
        }
    }

    public static void validateMonth(int month) {
        if (month < 1 || month > 12) {
            throw new IllegalArgumentException(ExceptionType.INVALID_INPUT_RANGE.getMessage());
        }
    }

    public static void validateWeek(String week) {

        if (week.length() > 1) {
            throw new IllegalArgumentException(ExceptionType.INVALID_INPUT.getMessage());
        }

        if (!week.matches(START_WEEK_REGEX)) {
            throw new IllegalArgumentException(ExceptionType.INVALID_INPUT_RANGE.getMessage());
        }
    }

    public static void validateWorker(List<String> workers) {

        if (workers.size() < 5 || workers.size() > 35) {
            throw new IllegalArgumentException(ExceptionType.INVALID_COUNT.getMessage());
        }

        for (String worker : workers) {
            if (worker.isBlank() || worker.length() > 5) {
                throw new IllegalArgumentException(ExceptionType.INVALID_NAME_LENGTH.getMessage());
            }
        }

        Set<String> target = new HashSet<>(workers);
        if (target.size() != workers.size()) {
            throw new IllegalArgumentException(ExceptionType.INVALID_DUPLICATE_ELEMENT.getMessage());
        }
    }
}
