package miem.projects.vulnerabilities.MINOR.FB;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class FB_BLOWFISH_KEY_SIZE {
    public static void main(String[] args) {
        try {
            incorrectTest();
            correctTest();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void incorrectTest() throws Exception {
        // Некорректно: ключ длиной 64 бита (недостаточно безопасно)
        KeyGenerator keygen = KeyGenerator.getInstance("Blowfish");
        keygen.init(64); // Ключ длиной 64 бита
        SecretKey secretKey = keygen.generateKey();

        System.out.println("Generated INSECURE Blowfish key (64-bit)");
        System.out.println("Key algorithm: " + secretKey.getAlgorithm());
        System.out.println("Key format: " + secretKey.getFormat());
    }

    public static void correctTest() throws Exception {
        // Корректно: ключ длиной 128 бит (рекомендуемый минимальный размер)
        KeyGenerator keygen = KeyGenerator.getInstance("Blowfish");
        keygen.init(128); // Ключ длиной 128 бит
        SecretKey secretKey = keygen.generateKey();

        System.out.println("Generated SECURE Blowfish key (128-bit)");
        System.out.println("Key algorithm: " + secretKey.getAlgorithm());
        System.out.println("Key format: " + secretKey.getFormat());
    }
}