package pairmatching;

import pairmatching.controller.PairController;
import pairmatching.view.InputView;
import pairmatching.view.OutputView;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        PairController pairController = new PairController(inputView, outputView);
        pairController.run();
    }
}
