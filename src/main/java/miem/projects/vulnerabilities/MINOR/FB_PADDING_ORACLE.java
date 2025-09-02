package miem.projects.vulnerabilities.MINOR.FB;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.IvParameterSpec;
import java.security.SecureRandom;

public class FB_PADDING_ORACLE {
    public static void main(String[] args) throws Exception {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() throws Exception {
        // Некорректно: использование CBC с PKCS5Padding (уязвимость Padding Oracle)
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKey secretKey = CryptoUtils.generateAESKey();
        byte[] iv = new byte[16];
        new SecureRandom().nextBytes(iv);

        byte[] encryptedData = "SensitiveData".getBytes();

        cipher.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(iv));
        try {
            byte[] decryptedData = cipher.doFinal(encryptedData);
            System.out.println("Decrypted with CBC (INSECURE): " + new String(decryptedData));
        } catch (Exception e) {
            System.out.println("Decryption failed (CBC, vulnerable to padding oracle)");
        }
    }

    public static void correctTest() throws Exception {
        // Корректно: использование GCM, защищающего от атак Padding Oracle
        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        SecretKey secretKey = CryptoUtils.generateAESKey();
        byte[] iv = new byte[12]; // стандартная длина IV для GCM
        new SecureRandom().nextBytes(iv);

        byte[] encryptedData = "SensitiveData".getBytes();

        GCMParameterSpec gcmSpec = new GCMParameterSpec(128, iv);
        cipher.init(Cipher.DECRYPT_MODE, secretKey, gcmSpec);
        try {
            byte[] decryptedData = cipher.doFinal(encryptedData);
            System.out.println("Decrypted with GCM (SECURE): " + new String(decryptedData));
        } catch (Exception e) {
            System.out.println("Decryption failed (but protected from padding oracle)");
        }
    }
}

class CryptoUtils {
    public static SecretKey generateAESKey() throws Exception {
        javax.crypto.KeyGenerator keygen = javax.crypto.KeyGenerator.getInstance("AES");
        keygen.init(128); // безопасный размер ключа
        return keygen.generateKey();
    }
}
