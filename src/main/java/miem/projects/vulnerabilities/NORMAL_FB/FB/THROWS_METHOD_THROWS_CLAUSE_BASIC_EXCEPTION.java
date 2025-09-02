package miem.projects.vulnerabilities.NORMAL.FB;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class THROWS_METHOD_THROWS_CLAUSE_BASIC_EXCEPTION {
    public static void main(String[] args) {
        try {
            incorrectTest();
        } catch (Exception e) {
            System.out.println("Caught generic Exception: " + e.getMessage());
        }

        try {
            correctTest();
        } catch (IOException e) {
            System.out.println("Caught IOException: " + e.getMessage());
        }
    }

    public static void incorrectTest() throws Exception {
        class FileProcessor {
            public String readFile(String path) throws Exception {
                if (!Files.exists(Path.of(path))) {
                    throw new Exception("File not found");
                }
                return Files.readString(Path.of(path));
            }
        }

        new FileProcessor().readFile("missing.txt");
    }

    public static void correctTest() throws IOException {
        class FileProcessor {
            public String readFile(String path) throws IOException, FileNotFoundException {
                if (!Files.exists(Path.of(path))) {
                    throw new FileNotFoundException("File not found: " + path);
                }
                return Files.readString(Path.of(path));
            }
        }

        new FileProcessor().readFile("missing.txt");
    }
}
