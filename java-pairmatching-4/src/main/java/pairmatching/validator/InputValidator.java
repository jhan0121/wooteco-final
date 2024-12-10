package pairmatching.validator;

import pairmatching.exception.ExceptionMessage;

public class InputValidator {

    private static final String OPTION_REGEX = "^[123Q]+$";
    private static final String EDUCATION_REGEX = "^[가-힣A-Za-z1-9, ]+$";

    private InputValidator() {

    }

    public static void validStartOption(String input) {
        if (!input.trim().matches(OPTION_REGEX)) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_INPUT_FORM.getMessage());
        }
    }

    public static void validEducationOption(String input) {
        if (!input.trim().matches(EDUCATION_REGEX)) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_INPUT_FORM.getMessage());
        }
    }

    public static void validRematchAnswer(String input) {
        if (!(input.trim().equals("네") || input.trim().equals("아니오"))) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_INPUT_FORM.getMessage());
        }
    }
}
