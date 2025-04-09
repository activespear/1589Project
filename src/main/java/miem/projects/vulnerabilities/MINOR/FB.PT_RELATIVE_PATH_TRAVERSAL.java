package miem.projects.vulnerabilities.MINOR.FB;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FB_PT_RELATIVE_PATH_TRAVERSAL {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        // Некорректно: использование относительных путей без проверки
        String userInput = "reports/../../etc/passwd";  // Может прийти из запроса
        File file = new File("data/" + userInput);
        
        if (file.exists()) {
            System.out.println("File accessed (INSECURE): " + file.getPath());
        }
    }

    public static void correctTest() {
        // Корректно: нормализация и проверка пути
        String userInput = "reports/../../etc/passwd";
        Path basePath = Paths.get("data/").toAbsolutePath().normalize();
        Path resolvedPath = basePath.resolve(userInput).normalize();
        
        if (!resolvedPath.startsWith(basePath)) {
            System.out.println("Relative path traversal attempt detected");
            return;
        }
        
        File file = resolvedPath.toFile();
        if (file.exists()) {
            System.out.println("File accessed securely: " + file.getPath());
        }
    }
}