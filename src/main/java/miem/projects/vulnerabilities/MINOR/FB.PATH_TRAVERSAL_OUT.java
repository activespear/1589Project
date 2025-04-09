package miem.projects.vulnerabilities.MINOR.FB;

import java.io.*;
import java.nio.file.*;

public class PATH_TRAVERSAL_OUT {
    public static void main(String[] args) {
        String userInput = "../../etc/shadow"; // Малициозный ввод
        incorrectTest(userInput);
        correctTest(userInput);
    }

    // Некорректная запись файла
    public static void incorrectTest(String fileName) {
        try {
            File file = new File("/var/tmp/export/" + fileName);
            Files.writeString(file.toPath(), "malicious content");
            System.out.println("File written (VULNERABLE)");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Защищенная запись файла
    public static void correctTest(String fileName) {
        try {
            Path basePath = Paths.get("/var/tmp/export/").normalize().toAbsolutePath();
            Path userPath = Paths.get(fileName).normalize();
            Path resolvedPath = basePath.resolve(userPath).normalize();
            
            if (!resolvedPath.startsWith(basePath)) {
                throw new SecurityException("Path traversal attempt detected");
            }
            
            Files.writeString(resolvedPath, "safe content");
            System.out.println("File written securely");
        } catch (IOException | SecurityException e) {
            e.printStackTrace();
        }
    }
}