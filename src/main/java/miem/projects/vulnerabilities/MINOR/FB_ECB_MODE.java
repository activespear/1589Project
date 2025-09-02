package miem.projects.vulnerabilities.MINOR.FB;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import java.security.SecureRandom;

public class FB_ECB_MODE {
    public static void main(String[] args) throws Exception {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() throws Exception {
        // Некорректно: использование режима ECB
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        SecretKey secretKey = CryptoUtils.generateAESKey();
        byte[] data = "SensitiveData".getBytes();

        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedData = cipher.doFinal(data);

        System.out.println("Encrypted with ECB (INSECURE): " + encryptedData.length + " bytes");
    }

    public static void correctTest() throws Exception {
        // Корректно: использование режима GCM вместо ECB
        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        SecretKey secretKey = CryptoUtils.generateAESKey();
        byte[] data = "SensitiveData".getBytes();

        byte[] iv = new byte[12]; // стандартная длина IV для GCM
        new SecureRandom().nextBytes(iv);

        GCMParameterSpec gcmSpec = new GCMParameterSpec(128, iv);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, gcmSpec);
        byte[] encryptedData = cipher.doFinal(data);

        System.out.println("Encrypted with GCM (SECURE): " + encryptedData.length + " bytes");
    }
}

class CryptoUtils {
    public static SecretKey generateAESKey() throws Exception {
        javax.crypto.KeyGenerator keygen = javax.crypto.KeyGenerator.getInstance("AES");
        keygen.init(128); // безопасный размер ключа
        return keygen.generateKey();
    }
}
