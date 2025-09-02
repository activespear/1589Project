package miem.projects.vulnerabilities.MINOR.FB;

import java.security.KeyPair;
import java.security.KeyPairGenerator;

public class FB_RSA_KEY_SIZE {
    public static void main(String[] args) {
        try {
            incorrectTest();
            correctTest();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void incorrectTest() throws Exception {
        // Некорректно: ключ длиной 1024 бита (недостаточно безопасно)
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(1024); // Ключ длиной 1024 бита
        KeyPair pair = keyGen.generateKeyPair();

        System.out.println("Generated INSECURE RSA key pair (1024-bit)");
        System.out.println("Private key algorithm: " + pair.getPrivate().getAlgorithm());
        System.out.println("Private key format: " + pair.getPrivate().getFormat());
        System.out.println("Public key algorithm: " + pair.getPublic().getAlgorithm());
        System.out.println("Public key format: " + pair.getPublic().getFormat());
    }

    public static void correctTest() throws Exception {
        // Корректно: ключ длиной 2048 бит (рекомендуемый минимальный размер)
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048); // Ключ длиной 2048 бит
        KeyPair pair = keyGen.generateKeyPair();

        System.out.println("Generated SECURE RSA key pair (2048-bit)");
        System.out.println("Private key algorithm: " + pair.getPrivate().getAlgorithm());
        System.out.println("Private key format: " + pair.getPrivate().getFormat());
        System.out.println("Public key algorithm: " + pair.getPublic().getAlgorithm());
        System.out.println("Public key format: " + pair.getPublic().getFormat());
    }
}