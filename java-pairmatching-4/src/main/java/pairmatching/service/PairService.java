package pairmatching.service;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;
import pairmatching.model.Match;
import pairmatching.model.Option;
import pairmatching.repository.EducationRepository;
import pairmatching.repository.EducationType;
import pairmatching.repository.Level;
import pairmatching.repository.PairRepository;
import pairmatching.util.Seperator;

public class PairService {

    private PairRepository pairRepository;
    private EducationRepository educationRepository;

    public PairService(PairRepository pairRepository, EducationRepository educationRepository) {
        this.pairRepository = pairRepository;
        this.educationRepository = educationRepository;
    }

    public void initData() {
        pairRepository.initPairData();
        educationRepository.initEducationData();
    }

    public void clearAll() {
        pairRepository.clearAll();
        initData();
    }

    public Option inputEducationOption(String educationInput) {
        String[] educationOption = Seperator.split(educationInput);
        educationRepository.validateEducationOption(educationOption);
        return new Option(educationOption[0], Level.findNum(educationOption[1]), educationOption[2]);
    }

    public boolean findPrePair(Option option) {
        // 특정 레벨의 특정 미션에 match 객체가 존재하는지 확인하면 됨
        Match target = pairRepository.findMatchByLevelAndEducationTypeAndMission(
                option.level(),
                option.educationType(),
                option.mission());
        if (target == null) {
            return false;
        }
        return true;
    }

    public Match match(String educationType, int level, String mission) {
        if (educationType.equals(EducationType.FRONT.getText())) {
            return matchFront(level, mission);
        }
        if (educationType.equals(EducationType.BACK.getText())) {
            return matchBack(level, mission);
        }
        return null;
    }

    private Match matchFront(int level, String mission) {
        List<String> frontCrew = pairRepository.findAllFrontCrew();
        List<String> shuffle = Randoms.shuffle(frontCrew);
        Match match = new Match(shuffle);
        pairRepository.saveMatch(match, EducationType.FRONT.getText(), level, mission);
        return match;
    }

    private Match matchBack(int level, String mission) {
        List<String> backCrew = pairRepository.findAllBackCrew();
        List<String> shuffle = Randoms.shuffle(backCrew);
        Match match = new Match(shuffle);
        pairRepository.saveMatch(match, EducationType.BACK.getText(), level, mission);
        return match;
    }

    public Match getMatch(String educationType, int level, String mission) {
        return pairRepository.getMatch(educationType, level, mission);
    }

    public void clearMatch(String educationType, int level, String mission) {
        if (educationType.equals(EducationType.FRONT.getText())) {
            pairRepository.clearFrontLevel(educationType, level, mission);
        }
        if (educationType.equals(EducationType.BACK.getText())) {
            pairRepository.clearBackLevel(educationType, level, mission);
        }
    }
}
