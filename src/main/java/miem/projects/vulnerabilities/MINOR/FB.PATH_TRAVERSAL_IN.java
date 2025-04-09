package miem.projects.vulnerabilities.MINOR.FB;

import java.io.*;
import java.nio.file.*;

public class PATH_TRAVERSAL_IN {
    public static void main(String[] args) {
        String userInput = "../../etc/passwd";  // Малициозный ввод
        incorrectTest(userInput);
        correctTest(userInput);
    }

    // Некорректная обработка пути
    public static void incorrectTest(String fileName) {
        try {
            File file = new File("/var/www/uploads/" + fileName);
            String content = Files.readString(file.toPath());
            System.out.println("File content: " + content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Корректная защита от Path Traversal
    public static void correctTest(String fileName) {
        try {
            Path basePath = Paths.get("/var/www/uploads/").normalize().toAbsolutePath();
            Path userPath = Paths.get(fileName).normalize();
            Path resolvedPath = basePath.resolve(userPath).normalize();
            
            if (!resolvedPath.startsWith(basePath)) {
                throw new SecurityException("Attempted path traversal attack");
            }
            
            String content = Files.readString(resolvedPath);
            System.out.println("Safe file content: " + content);
        } catch (IOException | SecurityException e) {
            e.printStackTrace();
        }
    }
}