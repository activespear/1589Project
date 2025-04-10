package miem.projects.vulnerabilities.MAJOR.FB;

import java.io.File;

public class DMI_HARDCODED_ABSOLUTE_FILENAME {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        // жестко прописанный абсолютный путь
        File file = new File("C:/Users/Username/Documents/myfile.txt");
        if (file.exists()) {
            System.out.println("Файл существует");
        } else {
            System.out.println("Файл не найден");
        }
    }

    public static void correctTest() {
        // использование относительного пути
        // Динамическое получение текущей рабочей директории
        String workingDirectory = System.getProperty("user.dir");
        File file = new File(workingDirectory + "/myfile.txt");
        if (file.exists()) {
            System.out.println("Файл существует");
        } else {
            System.out.println("Файл не найден");
        }
    }
}