package pairmatching.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import pairmatching.exception.ExceptionType;

public class Education {

    List<String> course;
    List<String> level;
    Map<String, List<String>> mission;

    public Education() {
        this.course = Arrays.stream(Course.values()).map(i -> i.getName()).toList();
        List<String> target = new ArrayList<>(Arrays.stream(Level.values()).map(i -> i.getName()).toList());
        target.sort(Comparator.naturalOrder());
        this.level = target;
        this.mission = new HashMap<>();
        mission.put(Level.LEVEL1.getName(), List.of("자동차경주", "로또", "숫자야구게임"));
        mission.put(Level.LEVEL2.getName(), List.of("장바구니", "결제", "지하철노선도"));
        mission.put(Level.LEVEL4.getName(), List.of("성능개선", "배포"));
    }

    public void validateEducation(List<String> education) {
        if (!course.contains(education.get(0))) {
            throw new IllegalArgumentException(ExceptionType.NOT_EXIST_ELEMENT.getMessage());
        }
        if (!level.contains(education.get(1))) {
            throw new IllegalArgumentException(ExceptionType.NOT_EXIST_ELEMENT.getMessage());
        }
        if (!mission.get(education.get(1)).contains(education.get(2))) {
            System.out.println(ExceptionType.NOT_EXIST_ELEMENT.getMessage());
            throw new IllegalArgumentException(ExceptionType.NOT_EXIST_ELEMENT.getMessage());
        }
    }

    public List<String> getCourse() {
        return course;
    }

    public List<String> getLevel() {
        return level;
    }

    public Map<String, List<String>> getMission() {
        return mission;
    }
}
