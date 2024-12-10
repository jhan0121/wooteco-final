package pairmatching.util;

import pairmatching.exception.ExceptionMessage;

public class Seperator {

    private Seperator() {

    }

    public static String[] split(String input) {
        validate(input);
        String[] result = input.split(",");
        for (int i = 0; i < result.length; i++) {
            result[i] = result[i].trim();
        }
        return result;
    }

    private static void validate(String value) {
        if (value.trim().isEmpty()) {
            throw new IllegalArgumentException(ExceptionMessage.EMPTY_VALUE.getMessage());
        }
        if (value.startsWith(",") || value.endsWith(",")) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_INPUT_FORM.getMessage());
        }
        if (value.contains(",,")) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_INPUT_FORM.getMessage());
        }
    }
}
