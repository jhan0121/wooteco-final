package pairmatching.controller;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import pairmatching.Validator.InputValidator;
import pairmatching.exception.ExceptionType;
import pairmatching.model.Course;
import pairmatching.model.Education;
import pairmatching.model.Match;
import pairmatching.util.Seperator;
import pairmatching.view.InputView;
import pairmatching.view.OutputView;

public class PairController {

    private InputView inputView;
    private OutputView outputView;

    public PairController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {

        Map<String, Match> match = new HashMap<>();
        Map<String, Map<String, Set<String>>> global = new HashMap<>();
        Education education = new Education();

        resolveStartOption(education, match, global);

    }

    private void resolveStartOption(Education education,
                                    Map<String, Match> match,
                                    Map<String, Map<String, Set<String>>> global) {
        String startInput = "";
        while (!startInput.equals("Q")) {
            try {
                startInput = inputView.inputStartOption();
                runOption(education, match, global, startInput);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void runOption(Education education,
                           Map<String, Match> match,
                           Map<String, Map<String, Set<String>>> global,
                           String startInput) throws IllegalArgumentException {
        if (startInput.equals("1")) {
            boolean flag = false;
            while (!flag) {
                outputView.printEducation(education);
                String courseInput = inputView.inputCourse();
                InputValidator.validateCourse(courseInput);
                List<String> option = Seperator.split(courseInput);
                education.validateEducation(option);
                flag = checkMatchData(match, global, courseInput, option);
                resolveMatch(flag, match, courseInput, global, option);
            }
            return;
        }
        if (startInput.equals("2")) {
            outputView.printEducation(education);
            String inputCourse = inputView.inputCourse();
            Match target = match.getOrDefault(inputCourse, null);
            if (target == null) {
                throw new IllegalArgumentException(ExceptionType.NOT_EXIST_MATCH.getMessage());
            }
            outputView.printMatch(target.getPair());
            return;
        }
        if (startInput.equals("3")) {
            match.clear();
            global.clear();
            outputView.printClear();
        }
    }

    private void resolveMatch(boolean flag,
                              Map<String, Match> match,
                              String courseInput,
                              Map<String, Map<String, Set<String>>> global,
                              List<String> option) {
        if (flag) {
            match(match, courseInput, global, option);
        }
    }

    private boolean checkMatchData(Map<String, Match> match,
                                   Map<String, Map<String, Set<String>>> global,
                                   String courseInput,
                                   List<String> option) {
        // 매칭 데이터 존재 여부 확인
        Match target = match.getOrDefault(courseInput, null);
        if (target == null) {
            return true;
        }
        String rematch = inputView.inputMatchAgain();
        if (rematch.equals("네")) {
            // 대상 매칭 데이터 제거
            Match matchCourse = match.get(courseInput);
            Map<String, Set<String>> matchPair = global.get(option.get(1));
            Deque<String> deque = new ArrayDeque<>(matchCourse.getPair());
            while (!deque.isEmpty()) {
                List<String> crews = new ArrayList<>();
                crews.add(deque.removeFirst());
                crews.add(deque.removeFirst());
                if (deque.size() == 1) {
                    crews.add(deque.removeFirst());
                }

                if (crews.size() == 2) {
                    Set<String> pair = matchPair.get(crews.get(0));
                    pair.remove(crews.get(1));
                    matchPair.put(option.get(1), pair);
                    pair = matchPair.get(crews.get(1));
                    pair.remove(crews.get(0));
                    matchPair.put(option.get(1), pair);
                    global.put(option.get(1), matchPair);
                }
                if (crews.size() == 3) {
                    Set<String> pair = matchPair.get(crews.get(0));
                    pair.remove(crews.get(1));
                    pair.remove(crews.get(2));
                    matchPair.put(option.get(1), pair);

                    pair = matchPair.get(crews.get(1));
                    pair.remove(crews.get(2));
                    pair.remove(crews.get(0));
                    matchPair.put(option.get(1), pair);

                    pair = matchPair.get(crews.get(2));
                    pair.remove(crews.get(0));
                    pair.remove(crews.get(1));
                    matchPair.put(option.get(1), pair);
                }
            }
            return true;
        }
        if (match.equals("아니오")) {
            return false;
        }
        return false;
    }

    private List<String> resolveCourse(String course) {
        if (course.equals(Course.FRONTEND.getName())) {
            return inputView.readFrontEndCrew();
        }
        if (course.equals(Course.BACKEND.getName())) {
            return inputView.readBackEndCrew();
        }
        throw new IllegalArgumentException(ExceptionType.NOT_EXIST_ELEMENT.getMessage());
    }

    private void match(Map<String, Match> match,
                       String courseInput,
                       Map<String, Map<String, Set<String>>> global,
                       List<String> option) {
        // 매칭하기
        List<String> crews = resolveCourse(option.get(0));
        List<String> shuffle = Randoms.shuffle(crews);
        int count = 0;
        while (count < 3) {
            boolean flag = validateMatch(shuffle, global.get(option.get(1)));
            if (flag) {
                break;
            }
            shuffle = Randoms.shuffle(shuffle);
            count++;
        }
        if (count == 3) {
            throw new IllegalArgumentException(ExceptionType.FAIL_PAIR.getMessage());
        }
        Match result = new Match(shuffle);

        // 매칭 저장하기
        match.put(courseInput, result);
        // global 매칭 업데이트
        updateGlobal(global, option, shuffle);
        outputView.printMatch(shuffle);
    }

    private boolean validateMatch(List<String> shuffle, Map<String, Set<String>> levelPair) {
        if (levelPair == null) {
            return true;
        }
        Deque<String> deque = new ArrayDeque<>(shuffle);
        while (!deque.isEmpty()) {
            List<String> crews = new ArrayList<>();
            crews.add(deque.removeFirst());
            crews.add(deque.removeFirst());
            if (deque.size() == 1) {
                crews.add(deque.removeFirst());
            }

            if (crews.size() == 2) {
                Set<String> target = levelPair.getOrDefault(crews.get(0), new HashSet<>());
                if (target.contains(crews.get(1))) {
                    return false;
                }
            }
            if (crews.size() == 3) {
                Set<String> target = levelPair.getOrDefault(crews.get(0), new HashSet<>());
                if (target.contains(crews.get(1)) || target.contains(crews.get(2))) {
                    return false;
                }
            }
        }
        return true;
    }

    private static void updateGlobal(Map<String, Map<String, Set<String>>> global,
                                     List<String> option,
                                     List<String> shuffle) {
        Map<String, Set<String>> levelPair = global.getOrDefault(option.get(1), new HashMap<>()); // 대상 레벨의 매칭 현황
        global.put(option.get(1), levelPair);

        Deque<String> deque = new ArrayDeque<>(shuffle);

        while (!deque.isEmpty()) {
            List<String> pair = new ArrayList<>();
            pair.add(deque.removeFirst());
            pair.add(deque.removeFirst());
            if (deque.size() == 1) {
                pair.add(deque.removeFirst());
            }
            if (pair.size() == 2) {
                Set<String> target = levelPair.getOrDefault(pair.get(0), new HashSet<>());
                target.add(pair.get(1));
                levelPair.put(pair.get(0), target);

                target = levelPair.getOrDefault(pair.get(1), new HashSet<>());
                target.add(pair.get(0));
                levelPair.put(pair.get(1), target);
            }

            if (pair.size() == 3) {
                Set<String> target = levelPair.getOrDefault(pair.get(0), new HashSet<>());
                target.addAll(List.of(pair.get(1), pair.get(2)));
                levelPair.put(pair.get(0), target);

                target = levelPair.getOrDefault(pair.get(1), new HashSet<>());
                target.addAll(List.of(pair.get(0), pair.get(2)));
                levelPair.put(pair.get(1), target);

                target = levelPair.getOrDefault(pair.get(2), new HashSet<>());
                target.addAll(List.of(pair.get(0), pair.get(1)));
                levelPair.put(pair.get(2), target);
            }
        }
        global.put(option.get(1), levelPair);
    }
}
