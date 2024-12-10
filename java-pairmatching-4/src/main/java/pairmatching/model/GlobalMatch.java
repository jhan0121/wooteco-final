package pairmatching.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class GlobalMatch {

    private Map<String, Set<String>> globalMatch;

    public GlobalMatch() {
        this.globalMatch = new HashMap<>();
    }

    public Map<String, Set<String>> getGlobalMatch() {
        return globalMatch;
    }
}
