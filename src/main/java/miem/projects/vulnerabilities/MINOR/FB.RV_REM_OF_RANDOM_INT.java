package miem.projects.vulnerabilities.MINOR.FB;

import java.util.Random;

public class FB_RV_REM_OF_RANDOM_INT {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        Random random = new Random();
        // Некорректно: остаток от деления случайного числа
        int num = random.nextInt() % 100;
        System.out.println("Incorrect random: " + num);
    }

    public static void correctTest() {
        Random random = new Random();
        // Корректно: использование nextInt(bound)
        int num = random.nextInt(100);
        System.out.println("Correct random: " + num);
        
        // Альтернативный корректный вариант для отрицательных чисел
        int num2 = Math.floorMod(random.nextInt(), 100);
        System.out.println("Alternative correct random: " + num2);
    }
}