package pairmatching.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import pairmatching.repository.EducationType;
import pairmatching.repository.Level;

public class Education {
    private final List<String> educationType;
    private final List<String> level;
    private final Map<String, List<String>> missionPerLevel;

    public Education() {
        this.educationType = new ArrayList<>();
        this.level = new ArrayList<>();
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
            missionPerLevel.put(level.getText(), new ArrayList<>());
        }
        missionPerLevel.get(Level.LV1.getText()).addAll(List.of("자동차경주", "로또", "숫자야구게임"));
        missionPerLevel.get(Level.LV2.getText()).addAll(List.of("장바구니", "결제", "지하철노선도"));
        missionPerLevel.get(Level.LV4.getText()).addAll(List.of("성능개선", "배포"));
    }

    public List<String> getEducationType() {
        return educationType;
    }

    public List<String> getLevel() {
        return level;
    }

    public Map<String, List<String>> getMissionPerLevel() {
        return missionPerLevel;
    }
}
