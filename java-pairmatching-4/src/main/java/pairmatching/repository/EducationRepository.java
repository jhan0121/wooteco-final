package pairmatching.repository;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import pairmatching.exception.ExceptionMessage;

public class EducationRepository {

    private final Set<String> educationType;
    private final Set<String> level;
    private final Map<String, Set<String>> missionPerLevel;
    private static EducationRepository educationRepository;

    private EducationRepository() {
        this.educationType = new HashSet<>();
        this.level = new HashSet<>();
        this.missionPerLevel = new HashMap<>();
    }

    public void initEducationData() {
        educationType.addAll(Arrays.stream(EducationType.values())
                .map(i -> i.getText()).toList());
        level.addAll(Arrays.stream(Level.values())
                .map(i -> i.getText()).toList());
        initMissionPerLevel();
    }

    private void initMissionPerLevel() {
        for (Level level : Level.values()) {
            missionPerLevel.put(level.getText(), new HashSet<>());
        }
        missionPerLevel.get(Level.LV1.getText()).addAll(List.of("자동차경주", "로또", "숫자야구게임"));
        missionPerLevel.get(Level.LV2.getText()).addAll(List.of("장바구니", "결제", "지하철노선도"));
        missionPerLevel.get(Level.LV4.getText()).addAll(List.of("성능개선", "배포"));
    }

    public Set<String> getEducationType() {
        return educationType;
    }

    public Set<String> getLevel() {
        return level;
    }

    public Map<String, Set<String>> getMissionPerLevel() {
        return missionPerLevel;
    }

    public static EducationRepository getInstance() {
        if (educationRepository == null) {
            educationRepository = new EducationRepository();
        }
        return educationRepository;
    }

    public void validateEducationOption(String[] educationOption) {
        for (String s : educationOption) {
            System.out.println(s);
        }
        if (!educationType.contains(educationOption[0])) {
            throw new IllegalArgumentException(ExceptionMessage.NOT_EXIST_ELEMENT.getMessage());
        }
        if (!level.contains(educationOption[1])) {
            throw new IllegalArgumentException(ExceptionMessage.NOT_EXIST_ELEMENT.getMessage());
        }
        if (!missionPerLevel.get(educationOption[1]).contains(educationOption[2])) {
            throw new IllegalArgumentException(ExceptionMessage.NOT_EXIST_ELEMENT.getMessage());
        }
    }
}
