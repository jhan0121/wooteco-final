package menu.controller;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.List;
import menu.model.Coach;
import menu.model.Menu;
import menu.model.Week;
import menu.util.Seperator;
import menu.validator.CategoryValidator;
import menu.validator.CoachValidator;
import menu.validator.MenuValidator;
import menu.view.InputView;
import menu.view.OutputView;

public class MenuController {

    private final InputView inputView;
    private final OutputView outputView;

    public MenuController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {

        outputView.printStart();
        
        Menu menu = new Menu();
        menu.initData();
        List<List<String>> menuPerCategory = menu.getMenu();
        List<String> category = menu.getCategories();
        List<String> selectCategories = new ArrayList<>();

        List<Coach> coaches = inputCoachData();

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
        outputView.printResult(coaches, selectCategories);
    }

    private static void selectMenuPerCoach(List<Coach> coaches, List<List<String>> menuPerCategory, int targetIndex) {
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

    private List<Coach> inputCoachData() {
        while (true) {
            try {
                String inputCoach = inputView.inputCoach();
                List<String> target = Seperator.split(inputCoach);
                CoachValidator.validateCoach(target);
                List<Coach> coaches = new ArrayList<>();
                for (String name : target) {
                    List<String> notEatableMenu = inputNotEatableMenu(name);
                    coaches.add(new Coach(name, notEatableMenu));
                }
                return coaches;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private List<String> inputNotEatableMenu(String name) {
        while (true) {
            try {
                String inputNotEatableMenu = inputView.inputNotEatableMenu(name);
                List<String> target = Seperator.split(inputNotEatableMenu);
                MenuValidator.validateMenu(target);
                return target;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
