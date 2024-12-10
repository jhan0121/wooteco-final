package pairmatching.view;

public enum FilePath {
    BACK("backend-crew.md"),
    FRONT("frontend-crew.md");

    private String url;

    FilePath(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
