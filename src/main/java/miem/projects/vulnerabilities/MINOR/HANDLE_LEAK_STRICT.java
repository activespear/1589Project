package miem.projects.vulnerabilities.MINOR;

import java.io.FileOutputStream;
import java.io.IOException;

public class HANDLE_LEAK_STRICT {
    public static void main(String[] args) throws IOException {
        incorrectTest();
        correctTest();
    }

    // Потенциально небезопасное: закрытый поток и повторное использование дескриптора
    public static void incorrectTest() throws IOException {
        FileOutputStream out = new FileOutputStream("output.txt");
        out.close();
        int predictedFd = 1; // Предполагаемый дескриптор
        new FileOutputStream(predictedFd); // Попытка повторного использования (опасно)
    }

    // Корректное использование: запись до закрытия потока
    public static void correctTest() throws IOException {
        FileOutputStream out = new FileOutputStream("output.txt");
        out.write("data".getBytes());
        out.close();
        System.out.println("Файл записан корректно");
    }
}
