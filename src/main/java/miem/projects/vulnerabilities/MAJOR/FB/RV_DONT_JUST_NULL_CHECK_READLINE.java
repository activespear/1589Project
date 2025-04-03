package miem.projects.vulnerabilities.MAJOR.FB;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RV_DONT_JUST_NULL_CHECK_READLINE {
    public static void main(String[] args) {
        incorrectTest();
        //correctTest();
    }

    public static void incorrectTest() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            if (reader.readLine() != null) { // Проверка, но строка отбрасывается
                System.out.println("Прочитана строка, но мы её не используем!");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void correctTest() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String line = reader.readLine(); // Читаем строку один раз
            if (line != null) {
                System.out.println("Прочитанная строка: " + line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}