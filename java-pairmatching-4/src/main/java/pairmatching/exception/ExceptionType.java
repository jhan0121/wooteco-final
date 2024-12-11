package pairmatching.exception;

public enum ExceptionType {
    EMPTY_VALUE("빈 입력값입니다."),
    INVALID_INPUT("입력 형태가 유효하지 않습니다."),
    INVALID_DUPLICATE_NAME("크루명은 중복된 값이 존재할 수 없습니다."),
    NOT_EXIST_ELEMENT("존재하지 않는 요소입니다."),
    NOT_EXIST_MATCH("매칭 이력이 없습니다."),
    FAIL_PAIR("매칭에 실패하였습니다.");

    private final String message;

    ExceptionType(String message) {
        this.message = "[ERROR] " + message;
    }

    public String getMessage() {
        return message;
    }
}