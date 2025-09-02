package miem.projects.vulnerabilities.MINOR.FB;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Logger;

public class FB_COMMAND_INJECTION {

    private static final Logger logger = Logger.getLogger(FB_COMMAND_INJECTION.class.getName());

    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    // Потенциально небезопасное выполнение команды
    public static void incorrectTest() {
        String filename = "user_input.txt"; // Эмуляция запроса
        logger.warning("Executing unsafe command: rm " + filename);
        // В реальном приложении это было бы:
        // Runtime.getRuntime().exec("rm " + filename);
    }

    // Корректная конструкция с проверкой имени файла
    public static void correctTest() {
        String filename = "user_input.txt"; // Эмуляция запроса
        if (isValidFileName(filename)) {
            try {
                Files.delete(Paths.get("/safe_dir/" + filename));
                logger.info("File deleted safely: " + filename);
            } catch (IOException e) {
                logger.severe("Failed to delete file: " + e.getMessage());
            }
        } else {
            logger.warning("Invalid filename: " + filename);
        }
    }

    // Простейшая проверка имени файла
    public static boolean isValidFileName(String filename) {
        return filename != null && filename.matches("^[a-zA-Z0-9._-]{1,20}$");
    }
}
