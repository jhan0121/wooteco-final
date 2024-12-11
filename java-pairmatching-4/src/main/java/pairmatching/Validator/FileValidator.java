package pairmatching.Validator;

import java.net.URL;
import pairmatching.exception.FileExceptionType;

public class FileValidator {

    private FileValidator() {

    }

    public static void validateFIleURL(URL fileURL) {
        if (fileURL == null) {
            System.out.printf(FileExceptionType.FILE_NOT_FOUND.getText(), "");
            throw new IllegalStateException(String.format(FileExceptionType.FILE_NOT_FOUND.getText(), ""));
        }
    }
}