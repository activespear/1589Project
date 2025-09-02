package miem.projects.vulnerabilities.MINOR.FB;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Logger;

public class FB_WEAK_FILENAMEUTILS {

    private static final Logger logger = Logger.getLogger(FB_WEAK_FILENAMEUTILS.class.getName());

    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    // Потенциально небезопасное использование FilenameUtils
    public static void incorrectTest() {
        String filename = "user_input.log"; // Эмуляция параметра запроса
        logger.warning("Processing unsafe filename: " + filename);
        // В реальном приложении это было бы:
        // if (FilenameUtils.getExtension(filename).equals("log")) {
        //     File file = new File("/logs/" + filename);
        //     readLogFile(file);
        // }
    }

    // Корректная конструкция с проверкой имени и безопасным путём
    public static void correctTest() {
        String filename = "user_input.log"; // Эмуляция параметра запроса
        if (isValidFileName(filename) && filename.endsWith(".log")) {
            Path filePath = Paths.get("/logs", filename).normalize();
            if (filePath.startsWith("/logs")) {
                readLogFile(filePath.toFile());
                logger.info("File processed safely: " + filename);
            } else {
                logger.warning("Attempt to access invalid path: " + filename);
            }
        } else {
            logger.warning("Invalid filename: " + filename);
        }
    }

    // Простейшая проверка имени файла
    public static boolean isValidFileName(String filename) {
        return filename != null && filename.matches("^[a-zA-Z0-9._-]{1,20}$");
    }

    // Заглушка для чтения файла
    public static void readLogFile(File file) {
        logger.info("Reading file: " + file.getAbsolutePath());
    }
}
