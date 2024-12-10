package pairmatching.presentation.file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FileReader {

    private static final String DIRECTORY = "src/main/resources/";
    private static final String BACKEND_CREW_FILE_NAME = "backend-crew.md";
    private static final String FRONTEND_CREW_FILE_NAME = "frontend-crew.md";

    public static List<String> readBackendCrews() {
        try {
            return getReadAllLines(BACKEND_CREW_FILE_NAME);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<String> readFrontendCrews() {
        try {
            return getReadAllLines(FRONTEND_CREW_FILE_NAME);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static List<String> getReadAllLines(final String fileName) throws IOException {
        return Files.readAllLines(Path.of(DIRECTORY + fileName));
    }

}
