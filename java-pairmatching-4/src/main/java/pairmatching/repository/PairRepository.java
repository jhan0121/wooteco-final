package pairmatching.repository;

import java.net.URL;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import pairmatching.exception.ExceptionMessage;
import pairmatching.model.GlobalMatch;
import pairmatching.model.Match;
import pairmatching.util.FileReader;
import pairmatching.view.FilePath;

public class PairRepository {

    private static PairRepository pairRepository;
    private final List<String> backCrew;
    private final List<String> frontCrew;
    private final List<Map<String, Match>> backPairPerLevel; //미션 별 매칭 현황: 레벨 -> 미션명 -> 매칭 현황
    private final List<Map<String, Match>> frontPairPerLevel;

    private final List<GlobalMatch> globalBackPair; // 레벨 별 매칭 현황
    private final List<GlobalMatch> globalFrontPair;

    private static final int FINAL_LEVEL = Level.values().length;

    private PairRepository() {
        this.backCrew = new ArrayList<>();
        this.frontCrew = new ArrayList<>();
        this.backPairPerLevel = new ArrayList<>(FINAL_LEVEL + 1);
        this.frontPairPerLevel = new ArrayList<>(FINAL_LEVEL + 1);
        this.globalBackPair = new ArrayList<>(FINAL_LEVEL + 1);
        this.globalFrontPair = new ArrayList<>(FINAL_LEVEL + 1);
        for (int i = 0; i <= FINAL_LEVEL; i++) {
            backPairPerLevel.add(null);
            frontPairPerLevel.add(null);
            globalBackPair.add(null);
            frontPairPerLevel.add(new HashMap<>());
            backPairPerLevel.add(new HashMap<>());
        }
    }

    public static PairRepository getInstance() {
        if (pairRepository == null) {
            pairRepository = new PairRepository();
        }
        return pairRepository;
    }

    public void clearFrontLevel(String educationType, int level, String mission) {
        Match match = frontPairPerLevel.get(level).get(mission);
        clearGlobalMatch(educationType, level, match);
        frontPairPerLevel.get(level).remove(mission);
    }

    public void clearBackLevel(String educationType, int level, String mission) {
        Match match = backPairPerLevel.get(level).get(mission);
        clearGlobalMatch(educationType, level, match);
        backPairPerLevel.get(level).remove(mission);
    }

    private void clearGlobalMatch(String educationType, int level, Match match) {
        if (match == null) {
            return;
        }

        List<String> pair = match.getPair();
        Deque<String> deque = new ArrayDeque<>(pair);
        Map<String, Set<String>> globalMatch = resolveGlobalMatch(educationType, level);

        while (!deque.isEmpty()) {
            List<String> crews = new ArrayList<>();
            crews.add(deque.getFirst());
            crews.add(deque.getFirst());
            if (deque.size() == 1) {
                crews.add(deque.getFirst());
            }

            for (int i = 0; i < crews.size(); i++) {
                for (int j = 0; j < crews.size() - 1; j++) {
                    globalMatch.get(crews.get(i)).remove(crews.get((i + j) % crews.size()));
                }
            }
        }

    }

    private Map<String, Set<String>> resolveGlobalMatch(String educationType, int level) {
        if (educationType.equals(EducationType.FRONT.getText())) {
            GlobalMatch globalMatchLevel = globalFrontPair.get(level);
            return globalMatchLevel.getGlobalMatch();
        }
        if (educationType.equals(EducationType.FRONT.getText())) {
            GlobalMatch globalMatchLevel = globalFrontPair.get(level);
            return globalMatchLevel.getGlobalMatch();
        }
        return null;
    }

    public void clearAll() {
        frontPairPerLevel.forEach(Map::clear);
        backPairPerLevel.forEach(Map::clear);
        globalFrontPair.clear();
        Collections.fill(globalFrontPair, new GlobalMatch());
        Collections.fill(globalBackPair, new GlobalMatch());
        globalBackPair.clear();
    }

    public List<String> findAllBackCrew() {
        return backCrew;
    }

    public List<String> findAllFrontCrew() {
        return frontCrew;
    }

    public Match findMatchByLevelAndEducationTypeAndMission(int level,
                                                            String educationType,
                                                            String mission) {

        if (educationType.equals(EducationType.FRONT.getText())) {
            return frontPairPerLevel.get(level).get(mission);
        }
        if (educationType.equals(EducationType.BACK.getText())) {
            return backPairPerLevel.get(level).get(mission);
        }
        return null;
    }

    public void initPairData() {
        List<String> frontCrewFromFile = readCrewFromFile(EducationType.FRONT);
        List<String> backCrewFromFile = readCrewFromFile(EducationType.BACK);
        this.frontCrew.addAll(frontCrewFromFile);
        this.backCrew.addAll(backCrewFromFile);
    }

    private List<String> readCrewFromFile(EducationType educationType) {
        URL pairFileURL = null;
        if (educationType == EducationType.FRONT) {
            pairFileURL = PairRepository.class.getClassLoader().getResource(FilePath.FRONT.getUrl());
        }
        if (educationType == EducationType.BACK) {
            pairFileURL = PairRepository.class.getClassLoader().getResource(FilePath.BACK.getUrl());
        }
        if (pairFileURL == null) {
            throw new IllegalArgumentException(ExceptionMessage.NOT_EXIST_ELEMENT.getMessage());
        }
        return FileReader.readFile(pairFileURL);
    }

    public void saveMatch(Match match, String educationType, int level, String mission) {
        if (educationType.equals(EducationType.FRONT.getText())) {
            Map<String, Match> pairPerLevel = frontPairPerLevel.get(level);
            pairPerLevel.put(mission, match);
        }
        if (educationType.equals(EducationType.BACK.getText())) {
            Map<String, Match> pairPerLevel = backPairPerLevel.get(level);
            pairPerLevel.put(mission, match);
        }
        saveGlobalMatch(match, level, educationType);
    }

    public void saveGlobalMatch(Match match, int level, String educationType) {
        if (educationType.equals(EducationType.FRONT.getText())) {
            GlobalMatch globalMatchLevel = globalFrontPair.get(level);
            List<String> pair = match.getPair();
            Map<String, Set<String>> globalMatch = globalMatchLevel.getGlobalMatch();
            saveGlobal(pair, globalMatch);
        }
        if (educationType.equals(EducationType.BACK.getText())) {
            GlobalMatch globalMatchLevel = globalBackPair.get(level);
            if (globalMatchLevel == null) {
                globalMatchLevel = new GlobalMatch();
            }
            List<String> pair = match.getPair();
            Map<String, Set<String>> globalMatch = globalMatchLevel.getGlobalMatch();
            saveGlobal(pair, globalMatch);
        }
    }

    private static void saveGlobal(List<String> pair, Map<String, Set<String>> globalMatch) {
        Deque<String> deque = new ArrayDeque<>(pair);

        while (!deque.isEmpty()) {
            if (deque.size() != 3) {
                String crew1 = deque.getFirst();
                String crew2 = deque.getFirst();
                globalMatch.getOrDefault(crew1, new HashSet<>()).add(crew2);
                globalMatch.getOrDefault(crew2, new HashSet<>()).add(crew1);
            }
            if (deque.size() == 3) {
                String crew1 = deque.getFirst();
                String crew2 = deque.getFirst();
                String crew3 = deque.getFirst();
                globalMatch.getOrDefault(crew1, new HashSet<>()).add(crew2);
                globalMatch.getOrDefault(crew1, new HashSet<>()).add(crew3);
                globalMatch.getOrDefault(crew2, new HashSet<>()).add(crew1);
                globalMatch.getOrDefault(crew2, new HashSet<>()).add(crew3);
                globalMatch.getOrDefault(crew3, new HashSet<>()).add(crew1);
                globalMatch.getOrDefault(crew3, new HashSet<>()).add(crew2);
            }
        }

    }

    public Match getMatch(String educationType, int level, String mission) {
        if (educationType.equals(EducationType.FRONT.getText())) {
            return frontPairPerLevel.get(level).getOrDefault(mission, null);
        }
        if (educationType.equals(EducationType.BACK.getText())) {
            return backPairPerLevel.get(level).getOrDefault(mission, null);
        }
        return null;
    }
}
