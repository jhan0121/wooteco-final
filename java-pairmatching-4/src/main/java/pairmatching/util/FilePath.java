package pairmatching.util;

public enum FilePath {
    FRONT("frontend-crew.md"),
    BACK("backend-crew.md");

    private final String path;

    FilePath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
