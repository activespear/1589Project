package miem.projects.vulnerabilities.NORMAL.FB;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class THROWS_METHOD_THROWS_CLAUSE_THROWABLE {
    public static void main(String[] args) {
        try {
            incorrectTest();
        } catch (Throwable t) {
            System.out.println("Caught Throwable: " + t.getMessage());
        }

        try {
            correctTest();
        } catch (IOException | IllegalArgumentException e) {
            System.out.println("Caught specific exception: " + e.getMessage());
        }
    }

    public static void incorrectTest() throws Throwable {
        class ConfigLoader {
            public void loadConfig(String path) throws Throwable {
                if (path == null) {
                    throw new Throwable("Path is null");
                }
                Files.readString(Path.of(path));
            }
        }

        new ConfigLoader().loadConfig(null);
    }

    public static void correctTest() throws IOException, IllegalArgumentException {
        class ConfigLoader {
            public void loadConfig(String path) throws IOException, IllegalArgumentException {
                if (path == null) {
                    throw new IllegalArgumentException("Path is null");
                }
                Files.readString(Path.of(path));
            }
        }

        new ConfigLoader().loadConfig(null);
    }
}
