package pairmatching.Validator;

import pairmatching.exception.ExceptionType;

public class InputValidator {

    private static final String START = "^[123Q]+$";
    private static final String COURSE = "^[가-힣A-Za-z1-9, ]+$";

    private InputValidator() {

    }

    public static void validateStartOption(String input) {
        if (!input.matches(START)) {
            throw new IllegalArgumentException(ExceptionType.INVALID_INPUT.getMessage());
        }
    }

    public static void validateCourse(String input) {
        if (!input.matches(COURSE)) {
            throw new IllegalArgumentException(ExceptionType.INVALID_INPUT.getMessage());
        }
    }

    public static void validateRematch(String input) {
        if (!(input.equals("네") || input.equals("아니오"))) {
            throw new IllegalArgumentException(ExceptionType.INVALID_INPUT.getMessage());
        }
    }
}
