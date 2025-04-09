package miem.projects.vulnerabilities.MINOR.FB;

import java.io.File;

public class RV_RETURN_VALUE_IGNORED_NO_SIDE_EFFECT {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    // Некорректное игнорирование возвращаемого значения
    public static void incorrectTest() {
        File file = new File("test.txt");
        file.delete(); // ОШИБКА: возвращаемое значение игнорируется
        System.out.println("File deletion attempted (status unknown)");
    }

    // Корректная обработка возвращаемого значения
    public static void correctTest() {
        File file = new File("test.txt");
        boolean deleted = file.delete();
        if (deleted) {
            System.out.println("File deleted successfully");
        } else {
            System.out.println("File deletion failed");
        }
    }
}