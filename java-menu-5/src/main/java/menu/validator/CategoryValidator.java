package menu.validator;

import java.util.List;

public class CategoryValidator {

    private CategoryValidator() {

    }

    public static boolean validateCategory(List<String> category, String target) {
        if (category.stream().filter(i -> i.equals(target)).count() > 2) {
            return true;
        }
        return false;
    }
}
