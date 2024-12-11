package menu.util;

import java.util.List;
import menu.exception.ExceptionType;

public class Seperator {

    private Seperator() {

    }

    public static List<String> split(String input) {
        validate(input);
        String[] target = input.split(",");
        for (int i = 0; i < target.length; i++) {
            target[i] = target[i].trim();
        }
        return List.of(target);
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
