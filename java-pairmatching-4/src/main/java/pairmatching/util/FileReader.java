package pairmatching.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import pairmatching.exception.FileExceptionMessage;
import pairmatching.validator.FileValidator;

public class FileReader {

    private FileReader() {

    }

    public static List<String> readFile(URL fileURL) {
        FileValidator.validateFIleURL(fileURL);
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(fileURL.toURI()))) {
            return reader.lines().toList();
        } catch (IOException | URISyntaxException e) {
            System.out.printf(FileExceptionMessage.FILE_NOT_READABLE.getText(), fileURL.getPath());
            throw new IllegalStateException(
                    String.format(FileExceptionMessage.FILE_NOT_READABLE.getText(), fileURL.getPath()));
        }
    }
}
