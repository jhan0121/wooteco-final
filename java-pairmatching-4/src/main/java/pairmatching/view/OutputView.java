package pairmatching.view;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import pairmatching.model.Education;
import pairmatching.model.Match;

public class OutputView {

    public void printEducation(Education education) {
        System.out.println("#############################################");
        System.out.print("과정: ");
        System.out.println(String.join(" | ", education.getEducationType()));
        System.out.println("미션: ");
        List<String> level = new ArrayList<>(education.getMissionPerLevel().keySet().stream().toList());
        level.sort(null);
        for (String i : level) {
            System.out.printf("  - %s: ", i);
            System.out.println(String.join(" | ", education.getMissionPerLevel().get(i)));
        }
        System.out.println("#############################################");
    }

    public void printMatch(Match match) {
        Deque<String> deque = new ArrayDeque<>(match.getPair());

        while (!deque.isEmpty()) {
            List<String> pair = new ArrayList<>();
            pair.add(deque.getFirst());
            pair.add(deque.getFirst());
            if (deque.size() == 1) {
                pair.add(deque.getFirst());
            }
            System.out.println(String.join(" : ", pair));
        }

    }
}
