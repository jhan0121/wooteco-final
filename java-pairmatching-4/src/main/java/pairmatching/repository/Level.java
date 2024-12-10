package pairmatching.repository;

import pairmatching.exception.ExceptionMessage;

public enum Level {
    LV1("레벨1", 1),
    LV2("레벨2", 2),
    LV3("레벨3", 3),
    LV4("레벨4", 4),
    LV5("레벨5", 5);

    private final String text;
    private final int num;

    Level(String text, int num) {
        this.text = text;
        this.num = num;
    }

    public String getText() {
        return text;
    }

    public int getNum() {
        return num;
    }

    public static int findNum(String text) {
        for (Level i : Level.values()) {
            if (i.getText().equals(text)) {
                return i.getNum();
            }
        }
        throw new IllegalArgumentException(ExceptionMessage.NOT_EXIST_ELEMENT.getMessage());
    }
}
