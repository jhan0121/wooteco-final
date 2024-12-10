package pairmatching.controller;

import pairmatching.exception.ExceptionMessage;
import pairmatching.model.Education;
import pairmatching.model.Match;
import pairmatching.model.Option;
import pairmatching.service.PairService;
import pairmatching.view.InputView;
import pairmatching.view.OutputView;

public class PairController {

    private InputView inputView;
    private OutputView outputView;
    private PairService pairService;

    public PairController(InputView inputView, OutputView outputView, PairService pairService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.pairService = pairService;
    }

    public void run() {
        pairService.initData();
        Education education = new Education();
        education.initEducationData();
        while (true) {
            String startOption = inputView.inputStartOption();
            if (startOption.equals("Q")) {
                return;
            }
            System.out.println(startOption);
            resolveOption(startOption, education);
        }
    }

    public void resolveOption(String startOption, Education education) {
        // 옵션 현황 출력 및 입력 받기
        if (startOption.equals("1")) {
            outputView.printEducation(education);
            Option option = inputEducationOption();
            // 입력 받은 옵션을 기반으로 기매칭 여부 확인
            boolean exist = pairService.findPrePair(option);

            // 존재하면 리매칭 여부 입력 받기
            boolean rematchAnswer = resolveRematchAnswer(exist, option);
            if (exist && !rematchAnswer) {
                return;
            }

            // 입력 받은 옵션을 기반으로 매칭 진행
            Match match = match(option);
            // 매칭 결과 출력하기
            outputView.printMatch(match);
            return;
        }
        if (startOption.equals("2")) {
            Option option = inputEducationOption();
            Match match = pairService.getMatch(option.educationType(), option.level(), option.mission());
            if (match == null) {
                return;
            }
            outputView.printMatch(match);
            return;
        }
        if (startOption.equals("3")) {
            clearAllMatch();
            return;
        }
        throw new IllegalArgumentException(ExceptionMessage.NOT_EXIST_ELEMENT.getMessage());
    }

    private boolean resolveRematchAnswer(boolean exist, Option option) {
        if (exist) {
            String rematch = inputView.inputRematch();
            // "네"일 경우, 옵션에 존재하는 매칭 초기화 하기(글로벌, 대상 미션)
            if (rematch.equals("네")) {
                clearMatch(option);
                return true;
            }
            if (rematch.equals("아니오")) {
                return false;
            }
        }
        return false;
    }

    private Option inputEducationOption() {
        String educationInput = inputView.inputEducationOption();
        return pairService.inputEducationOption(educationInput);
    }

    private Match match(Option option) {
        return pairService.match(option.educationType(), option.level(), option.mission());
    }

    private void clearMatch(Option option) {
        pairService.clearMatch(option.educationType(), option.level(), option.mission());
    }

    private void clearAllMatch() {
        pairService.clearAll();
    }
}
