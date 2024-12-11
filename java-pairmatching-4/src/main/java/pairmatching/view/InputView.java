package pairmatching.view;

import camp.nextstep.edu.missionutils.Console;
import java.net.URL;
import java.util.List;
import pairmatching.Validator.InputValidator;
import pairmatching.util.FilePath;
import pairmatching.util.FileReader;

public class InputView {

    private String readLine() {
        return Console.readLine();
    }

    public String inputStartOption() {
        while (true) {
            try {
                System.out.println(InputMessage.START.getText());
                String input = readLine();
                InputValidator.validateStartOption(input);
                return input;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public String inputCourse() {
        while (true) {
            try {
                System.out.println(InputMessage.OPTION.getText());
                String input = readLine();
                InputValidator.validateCourse(input);
                return input;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public String inputMatchAgain() {
        while (true) {
            try {
                System.out.println(InputMessage.REMATCH.getText());
                String input = readLine();
                InputValidator.validateRematch(input);
                return input;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public List<String> readBackEndCrew() {
        URL FileURL = InputView.class.getClassLoader().getResource(FilePath.BACK.getPath());
        return FileReader.readFile(FileURL);
    }

    public List<String> readFrontEndCrew() {
        URL FileURL = InputView.class.getClassLoader().getResource(FilePath.FRONT.getPath());
        return FileReader.readFile(FileURL);
    }
}
