package oncall.validator;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import oncall.exception.ExceptionType;

public class RotateValidator {

    private RotateValidator() {

    }

    public static void validateRotate(List<String> normalRotate, List<String> holidayRotate) {
        Set<String> targetNormal = new HashSet<>(normalRotate);
        Set<String> targetHoliday = new HashSet<>(holidayRotate);
        targetNormal.retainAll(targetHoliday);
        if (targetNormal.size() != normalRotate.size()) {
            throw new IllegalArgumentException(ExceptionType.INVALID_INPUT_COUNT.getMessage());
        }
    }
}
