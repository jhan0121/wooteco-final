package pairmatching.view;

import camp.nextstep.edu.missionutils.Console;
import pairmatching.validator.InputValidator;

public class InputView {

    private String readLine() {
        return Console.readLine();
    }

    public String inputStartOption() {
        while (true) {
            try {
                System.out.println(InputMessage.INPUT_OPTION.getText());
                String input = readLine();
                InputValidator.validStartOption(input);
                return input;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public String inputEducationOption() {
        while (true) {
            try {
                System.out.println(InputMessage.SELECT_SUB_OPTION.getText());
                String input = readLine();
                InputValidator.validEducationOption(input);
                return input;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public String inputRematch() {
        while (true) {
            try {
                System.out.println(InputMessage.REMATCH.getText());
                String input = readLine();
                InputValidator.validRematchAnswer(input);
                return input;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
