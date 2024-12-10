package pairmatching.model;

import java.util.List;

public class Match {

    private final List<String> pair;

    public Match(List<String> pair) {
        this.pair = pair;
    }

    public List<String> getPair() {
        return pair;
    }
}
