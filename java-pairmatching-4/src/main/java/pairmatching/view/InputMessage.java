package pairmatching.view;

public enum InputMessage {
    START("""
            기능을 선택하세요.
            1. 페어 매칭
            2. 페어 조회
            3. 페어 초기화
            Q. 종료"""),
    OPTION("""
            과정, 레벨, 미션을 선택하세요.
            ex) 백엔드, 레벨1, 자동차경주"""),
    RESULT("페어 매칭 결과입니다."),
    REMATCH("""
            매칭 정보가 있습니다. 다시 매칭하시겠습니까?
            네 | 아니오""");


    private final String text;

    InputMessage(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
