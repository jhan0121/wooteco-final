package pairmatching.validator;

import java.net.URL;
import pairmatching.exception.FileExceptionMessage;

public class FileValidator {

    private FileValidator() {

    }

    public static void validateFIleURL(URL fileURL) {
        if (fileURL == null) {
            System.out.printf(FileExceptionMessage.FILE_NOT_FOUND.getText(), "");
            throw new IllegalStateException(String.format(FileExceptionMessage.FILE_NOT_FOUND.getText(), ""));
        }
    }
}
