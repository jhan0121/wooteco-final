package menu.validator;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import menu.exception.ExceptionType;

public class CoachValidator {

    private CoachValidator() {

    }

    public static void validateCoach(List<String> coaches) {
        if (coaches.size() < 2 || coaches.size() > 5) {
            throw new IllegalArgumentException(ExceptionType.INVALID_RANGE_NUMBER.getMessage());
        }
        Set<String> target = new HashSet<>(coaches);
        if (coaches.size() != target.size()) {
            throw new IllegalArgumentException(ExceptionType.INVALID_DUPLICATE_COACH.getMessage());
        }
        for (String coach : coaches) {
            if (coach.length() < 2 || coach.length() > 4) {
                throw new IllegalArgumentException(ExceptionType.INVALID_COACH_NAME_RANGE.getMessage());
            }
        }
    }
}
