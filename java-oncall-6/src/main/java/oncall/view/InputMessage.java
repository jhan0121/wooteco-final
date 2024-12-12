package oncall.view;

public enum InputMessage {
    MONTH_AND_DAY("비상 근무를 배정할 월과 시작 요일을 입력하세요> "),
    NORMAL_WORKER("평일 비상 근무 순번대로 사원 닉네임을 입력하세요> "),
    HOLIDAY_WORKER("휴일 비상 근무 순번대로 사원 닉네임을 입력하세요> ");

    private final String text;

    InputMessage(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
