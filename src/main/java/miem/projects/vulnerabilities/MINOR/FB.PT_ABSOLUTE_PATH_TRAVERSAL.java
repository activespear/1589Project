package miem.projects.vulnerabilities.MINOR.FB;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FB_PT_ABSOLUTE_PATH_TRAVERSAL {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        // Некорректно: использование пользовательского ввода без проверки
        String userInput = "../../etc/passwd";  // Может прийти из запроса
        File file = new File("/var/www/uploads/" + userInput);
        
        if (file.exists()) {
            System.out.println("File accessed (INSECURE): " + file.getPath());
        }
    }

    public static void correctTest() {
        // Корректно: нормализация и проверка пути
        String userInput = "../../etc/passwd";  // Может прийти из запроса
        Path basePath = Paths.get("/var/www/uploads/").normalize();
        Path resolvedPath = basePath.resolve(userInput).normalize();
        
        if (!resolvedPath.startsWith(basePath)) {
            System.out.println("Path traversal attempt detected");
            return;ы
        }
        
        File file = resolvedPath.toFile();
        if (file.exists()) {
            System.out.println("File accessed securely: " + file.getPath());
        }
    }
}