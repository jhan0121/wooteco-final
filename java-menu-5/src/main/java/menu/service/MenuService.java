package menu.service;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.List;
import menu.model.Coach;
import menu.model.Menu;
import menu.model.Week;
import menu.validator.CategoryValidator;
import menu.validator.MenuValidator;

public class MenuService {

    private final Menu menu;
    private final List<String> selectCategories;

    public MenuService() {
        this.menu = new Menu();
        this.selectCategories = new ArrayList<>();
        menu.initData();
    }

    public void selectMenu(List<Coach> coaches) {
        List<List<String>> menuPerCategory = menu.getMenu();
        List<String> category = menu.getCategories();

        // 월 - 금 메뉴 카테고리 선택하기
        for (int i = 0; i < Week.values().length; i++) {
            // 현재 요일 카테고리 선택
            boolean flag = true;
            int targetIndex = 0;
            String target = "";

            while (flag) {
                targetIndex = Randoms.pickNumberInRange(1, 5);
                target = category.get(targetIndex);
                flag = CategoryValidator.validateCategory(selectCategories, target);
            }
            selectCategories.add(target);
            selectMenuPerCoach(coaches, menuPerCategory, targetIndex);
        }
    }

    private void selectMenuPerCoach(List<Coach> coaches, List<List<String>> menuPerCategory, int targetIndex) {
        // 각 코치별 메뉴 랜덤 선택
        for (Coach coach : coaches) {
            List<String> categoryMenu = menuPerCategory.get(targetIndex);
            String targetMenu = "";
            boolean flag = true;
            while (flag) {
                targetMenu = Randoms.shuffle(categoryMenu).get(0);
                flag = MenuValidator.validateCoachMenu(coach, targetMenu);
            }
            // 통과하면 메뉴 추가
            coach.addMenu(targetMenu);
        }
    }

    public List<String> getSelectCategories() {
        return selectCategories;
    }
}
