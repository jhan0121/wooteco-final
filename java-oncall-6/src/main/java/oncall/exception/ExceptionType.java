package oncall.exception;

public enum ExceptionType {
    EMPTY_VALUE("값이 비어있습니다."),
    INVALID_INPUT("유효하지 않은 입력 형식입니다."),
    INVALID_INPUT_RANGE("유효하지 않은 입력 범위입니다."),
    INVALID_NAME_LENGTH("비상 근무자명은 5자 이하여야 합니다."),
    INVALID_COUNT("비상 근무자의 수는 5명 이상 35명 이하여야 합니다."),
    INVALID_DUPLICATE_ELEMENT("비상 근무자는 중복된 이름이 존재할 수 없습니다."),
    INVALID_INPUT_COUNT("비상 근무자는 평일/휴일 순번에 각각 1회씩 이름이 존재해야 합니다.");

    private final String message;

    ExceptionType(String message) {
        this.message = "[ERROR] " + message;
    }

    public String getMessage() {
        return message;
    }
}