package menu.view;

import camp.nextstep.edu.missionutils.Console;
import menu.validator.InputValidator;

public class InputView {

    private String readLine() {
        return Console.readLine();
    }

    public String inputCoach() {
        while (true) {
            try {
                System.out.println(InputMessage.COACH.getText());
                String input = readLine();
                InputValidator.validateCoach(input);
                return input;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public String inputNotEatableMenu(String name) {
        while (true) {
            try {
                System.out.printf(InputMessage.NOT_EATABLE_MENU.getText(), name);
                String input = readLine();
                InputValidator.validateNotEatableMenu(input);
                return input;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
