package pairmatching.repository;

public enum EducationType {
    FRONT("프론트엔드"),
    BACK("백엔드");

    private String text;

    EducationType(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
