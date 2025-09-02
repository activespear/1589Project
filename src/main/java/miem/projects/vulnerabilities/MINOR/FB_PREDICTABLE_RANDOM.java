package miem.projects.vulnerabilities.MINOR.FB;

import java.security.SecureRandom;
import java.util.Random;

public class FB_PREDICTABLE_RANDOM {
    public static void main(String[] args) {
        System.out.println("Небезопасный токен: " + generateToken());
        System.out.println("Безопасный токен: " + generateSecureToken());
    }

    // Потенциально небезопасное
    public static int generateToken() {
        return new Random().nextInt(1000);  // Предсказуемый ГПСЧ
    }

    // Корректная конструкция
    public static int generateSecureToken() {
        return new SecureRandom().nextInt(1000);  // Криптографически стойкий ГПСЧ
    }
}
