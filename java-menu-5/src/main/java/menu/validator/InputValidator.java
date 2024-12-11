package menu.validator;

public class InputValidator {

    private static final String REGEX = "^[가-힣A-Za-z, ]+$";

    public static void validateCoach(String input) {
        if (!input.matches(REGEX)) {
            throw new IllegalArgumentException();
        }
    }

    public static void validateNotEatableMenu(String input) {
        if (!input.matches(REGEX)) {
            throw new IllegalArgumentException();
        }
    }
}
