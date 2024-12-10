package pairmatching;

import pairmatching.controller.PairController;
import pairmatching.repository.EducationRepository;
import pairmatching.repository.PairRepository;
import pairmatching.service.PairService;
import pairmatching.view.InputView;
import pairmatching.view.OutputView;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        PairService pairService = new PairService(PairRepository.getInstance(), EducationRepository.getInstance());
        PairController pairController = new PairController(inputView, outputView, pairService);
        pairController.run();
    }
}
