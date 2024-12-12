package oncall.model;

public enum Week {
    MON("월"),
    TUE("화"),
    WED("수"),
    TUR("목"),
    FRI("금"),
    SAT("토"),
    SUN("일");

    private final String text;

    Week(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
