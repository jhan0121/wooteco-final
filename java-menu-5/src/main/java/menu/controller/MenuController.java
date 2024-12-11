package menu.controller;

import java.util.ArrayList;
import java.util.List;
import menu.model.Coach;
import menu.service.MenuService;
import menu.util.Seperator;
import menu.validator.CoachValidator;
import menu.validator.MenuValidator;
import menu.view.InputView;
import menu.view.OutputView;

public class MenuController {

    private final InputView inputView;
    private final OutputView outputView;
    private final MenuService menuService;

    public MenuController(InputView inputView, OutputView outputView, MenuService menuService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.menuService = menuService;
    }

    public void run() {
        outputView.printStart();
        List<Coach> coaches = inputCoachData();
        menuService.selectMenu(coaches);
        outputView.printResult(coaches, menuService.getSelectCategories());
    }

    private List<Coach> inputCoachData() {
        while (true) {
            try {
                String inputCoach = inputView.inputCoach();
                List<String> target = Seperator.split(inputCoach);
                CoachValidator.validateCoach(target);
                List<Coach> coaches = new ArrayList<>();
                resolveNotEatableMenu(target, coaches);
                return coaches;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void resolveNotEatableMenu(List<String> target, List<Coach> coaches) {
        for (String name : target) {
            List<String> notEatableMenu = inputNotEatableMenu(name);
            coaches.add(new Coach(name, notEatableMenu));
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
