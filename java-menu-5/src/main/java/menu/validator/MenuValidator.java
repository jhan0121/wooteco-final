package menu.validator;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import menu.exception.ExceptionType;
import menu.model.Coach;

public class MenuValidator {

    private MenuValidator() {

    }

    public static void validateMenu(List<String> menu) {
        if (menu.size() > 2) {
            throw new IllegalArgumentException(ExceptionType.INVALID_MENU_COUNT.getMessage());
        }
        Set<String> target = new HashSet<>(menu);
        if (menu.size() != target.size()) {
            throw new IllegalArgumentException(ExceptionType.INVALID_DUPLICATE_MENU.getMessage());
        }
    }

    public static boolean validateCoachMenu(Coach coach, String targetMenu) {
        List<String> notEatableMenu = coach.getNotEatableMenu();
        List<String> selectMenu = coach.getSelectMenu();

        if (notEatableMenu.stream().filter(i -> i.equals(targetMenu)).count() > 0) {
            return true;
        }
        if (selectMenu.stream().filter(i -> i.equals(targetMenu)).count() > 0) {
            return true;
        }
        return false;
    }
}
