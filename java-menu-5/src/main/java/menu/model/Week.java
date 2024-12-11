package menu.model;

public enum Week {
    MON("월요일"),
    TUE("화요일"),
    WEN("수요일"),
    TUR("목요일"),
    FRI("금요일");

    private final String text;

    Week(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
