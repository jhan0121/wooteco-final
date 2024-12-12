package oncall;

import oncall.controller.WorkController;
import oncall.view.InputView;
import oncall.view.OutputView;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        WorkController workController = new WorkController(inputView, outputView);
        workController.run();
    }
}
