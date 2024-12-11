package pairmatching.exception;

public enum FileExceptionType {
    FILE_NOT_FOUND("대상 파일이 존재하지 않습니다: %s\n"),
    FILE_NOT_READABLE("대상 파일을 읽지 못했습니다: %s\n");

    private final String text;

    FileExceptionType(String text) {
        this.text = "[ERROR] " + text;
    }

    public String getText() {
        return text;
    }
}

