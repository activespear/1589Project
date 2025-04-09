package miem.projects.vulnerabilities.MINOR.FB;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import java.security.*;

public class STATIC_IV {
    private static final byte[] STATIC_IV = new byte[16]; // Опасность: статический вектор инициализации
    
    public static void main(String[] args) throws Exception {
        incorrectTest();
        correctTest();
    }

    // Некорректное использование статического IV
    public static void incorrectTest() throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        SecretKey key = keyGen.generateKey();
        
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(STATIC_IV)); // Уязвимость!
        
        byte[] encrypted = cipher.doFinal("Секретное сообщение".getBytes());
        System.out.println("Небезопасное шифрование завершено");
    }

    // Корректная реализация с динамическим IV
    public static void correctTest() throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        SecretKey key = keyGen.generateKey();
        
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        
        // Безопасно: генерация уникального IV для каждой операции
        SecureRandom random = new SecureRandom();
        byte[] iv = new byte[16];
        random.nextBytes(iv);
        
        cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(iv));
        byte[] encrypted = cipher.doFinal("Секретное сообщение".getBytes());
        System.out.println("Безопасное шифрование завершено");
    }
}