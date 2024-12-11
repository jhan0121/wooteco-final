package pairmatching.util;

import java.util.List;
import pairmatching.exception.ExceptionType;

public class Seperator {

    private Seperator() {
        
    }

    public static List<String> split(String input) {
        validate(input);
        String[] split = input.split(",");
        for (int i = 0; i < split.length; i++) {
            split[i] = split[i].trim();
        }
        return List.of(split);
    }

    private static void validate(String value) {
        if (value.trim().isEmpty()) {
            throw new IllegalArgumentException(ExceptionType.EMPTY_VALUE.getMessage());
        }
        if (value.startsWith(",") || value.endsWith(",")) {
            throw new IllegalArgumentException(ExceptionType.INVALID_INPUT.getMessage());
        }
        if (value.contains(",,")) {
            throw new IllegalArgumentException(ExceptionType.INVALID_INPUT.getMessage());
        }
    }
}
