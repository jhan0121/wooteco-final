package pairmatching.exception;

public enum ExceptionMessage {
    EMPTY_VALUE("빈 입력값입니다."),
    INVALID_INPUT_FORM("입력 형태가 맞지 않습니다."),
    INVALID_RANGE_NUMBER("입력 범위가 초과되었습니다."),
    INVALID_DUPLICATE_NAME("멤버 이름은 중복된 값이 존재할 수 없습니다."),
    INVALID_NUMBER_TYPE("입력값은 정수여야 합니다"),
    NOT_EXIST_ELEMENT("존재하지 않는 값입니다.");

    private String message;

    ExceptionMessage(String message) {
        this.message = "[ERROR] " + message;
    }

    public String getMessage() {
        return message;
    }
}