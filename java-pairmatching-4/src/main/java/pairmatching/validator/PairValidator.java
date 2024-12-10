package pairmatching.validator;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;
import java.util.Set;
import pairmatching.model.GlobalMatch;
import pairmatching.model.Match;

public class PairValidator {

    public static boolean validatePrePair(GlobalMatch globalMatch, Match match) {
        Map<String, Set<String>> global = globalMatch.getGlobalMatch();
        Deque<String> deque = new ArrayDeque<>(match.getPair());
        while (!deque.isEmpty()) {
            boolean flag = false;
            flag = findPrePair(deque, flag, global);
            if (flag) {
                return true;
            }
        }
        return false;
    }

    private static boolean findPrePair(Deque<String> deque, boolean flag, Map<String, Set<String>> global) {
        if (deque.size() != 3) {
            String crew1 = deque.getFirst();
            String crew2 = deque.getFirst();
            flag = validate(global, crew1, crew2);
        }
        if (deque.size() == 3) {
            String crew1 = deque.getFirst();
            String crew2 = deque.getFirst();
            String crew3 = deque.getFirst();
            flag = validate(global, crew1, crew2, crew3);
        }
        return flag;
    }

    private static boolean validate(Map<String, Set<String>> global, String crew1, String crew2) {
        return global.get(crew1).contains(crew2) || global.get(crew2).contains(crew1);
    }

    private static boolean validate(Map<String, Set<String>> global, String crew1, String crew2, String crew3) {
        return global.get(crew1).contains(crew2) || global.get(crew2).contains(crew3) || global.get(crew3)
                .contains(crew1);
    }
}
