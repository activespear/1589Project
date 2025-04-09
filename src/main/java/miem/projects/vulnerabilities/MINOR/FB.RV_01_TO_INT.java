package miem.projects.vulnerabilities.MINOR.FB;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.security.SecureRandom;

public class RV_01_TO_INT {
    public static void main(String[] args) {
        incorrectExample();
        correctExamples();
    }

    // Некорректный пример
    public static void incorrectExample() {
        int badRandom = (int)Math.random();  // FB.RV_01_TO_INT
        System.out.println("Некорректный случайный номер: " + badRandom);  // Всегда 0
    }

    // Корректные примеры
    public static void correctExamples() {
        // Способ 1: Math.random с масштабированием
        int goodRandom1 = (int)(Math.random() * 100);  // 0-99
        System.out.println("Правильный случай 1: " + goodRandom1);
        
        // Способ 2: java.util.Random
        int goodRandom2 = new Random().nextInt(100);  // 0-99
        System.out.println("Правильный случай 2: " + goodRandom2);
        
        // Способ 3: ThreadLocalRandom (Java 7+)
        int goodRandom3 = ThreadLocalRandom.current().nextInt(100);  // 0-99
        System.out.println("Правильный случай 3: " + goodRandom3);
        
        // Способ 4: SecureRandom
        int goodRandom4 = new SecureRandom().nextInt(100);  // 0-99
        System.out.println("Правильный случай 4: " + goodRandom4);
    }
}