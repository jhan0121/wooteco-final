package pairmatching.view;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Map;
import pairmatching.model.Education;

public class OutputView {

    public void printEducation(Education education) {
        List<String> course = education.getCourse();
        List<String> level = education.getLevel();
        Map<String, List<String>> mission = education.getMission();
        System.out.println("#############################################");
        System.out.print("과정: ");
        System.out.println(String.join(" | ", course));
        for (String i : level) {
            System.out.printf("  - %s: ", i);
            System.out.println(String.join(" | ", mission.getOrDefault(i, new ArrayList<>())));
        }
        System.out.println("############################################");
    }

    public void printMatch(List<String> match) {
        Deque<String> deque = new ArrayDeque<>(match);
        System.out.println("페어 매칭 결과입니다.");
        while (!deque.isEmpty()) {
            List<String> crews = new ArrayList<>();
            crews.add(deque.removeFirst());
            crews.add(deque.removeFirst());
            if (deque.size() == 1) {
                crews.add(deque.removeFirst());
            }
            System.out.println(String.join(" : ", crews));
        }
        System.out.println();
    }

    public void printClear() {
        System.out.println("초기화 되었습니다.");
    }
}
