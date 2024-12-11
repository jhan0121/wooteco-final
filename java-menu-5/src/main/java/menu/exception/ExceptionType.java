package menu.exception;

public enum ExceptionType {
    EMPTY_VALUE("빈 입력값입니다."),
    INVALID_INPUT("유효하지 않은 입력 형태입니다."),
    INVALID_RANGE_NUMBER("코치의 수는 2명에서 5명 사이여야 합니다."),
    INVALID_COACH_NAME_RANGE("코치의 이름은 최소 2글자, 최대 4글자여야 합니다."),
    INVALID_DUPLICATE_COACH("중복된 코치명은 존재할 수 없습니다."),
    INVALID_DUPLICATE_MENU("중복된 메뉴명은 존재할 수 없습니다."),
    INVALID_MENU_COUNT("못먹는 메뉴는 2개까지 가능합니다.");

    private String message;

    ExceptionType(String message) {
        this.message = "[ERROR] " + message;
    }

    public String getMessage() {
        return message;
    }
}