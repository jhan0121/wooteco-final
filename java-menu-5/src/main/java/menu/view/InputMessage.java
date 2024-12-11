package menu.view;

public enum InputMessage {
    COACH("코치의 이름을 입력해 주세요. (, 로 구분)"),
    NOT_EATABLE_MENU("\n%s(이)가 못 먹는 메뉴를 입력해 주세요.\n");

    private final String text;

    InputMessage(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
