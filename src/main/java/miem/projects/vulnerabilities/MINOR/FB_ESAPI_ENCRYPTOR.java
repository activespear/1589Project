package miem.projects.vulnerabilities.MINOR.FB;

import org.owasp.esapi.ESAPI;
import org.owasp.esapi.Encryptor;

import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.util.Base64;

public class FB_ESAPI_ENCRYPTOR {
    public static void main(String[] args) throws Exception {
        String sensitiveData = "SuperSecretData";

        incorrectTest(sensitiveData);
        correctTest(sensitiveData);
    }

    public static void incorrectTest(String sensitiveData) throws Exception {
        // Некорректно: шифрование без аутентификации (может быть изменено злоумышленником)
        Encryptor encryptor = ESAPI.crypto();
        String encryptedData = encryptor.encrypt(sensitiveData);
        System.out.println("Encrypted without authentication (INSECURE): " + encryptedData);
    }

    public static void correctTest(String sensitiveData) throws Exception {
        // Корректно: использование AES/GCM для аутентифицированного шифрования
        Encryptor encryptor = ESAPI.crypto();

        // Генерация безопасного ключа и IV
        byte[] keyBytes = new byte[16]; // 128 бит
        byte[] ivBytes = new byte[12];  // стандартная длина IV для GCM
        SecureRandom random = new SecureRandom();
        random.nextBytes(keyBytes);
        random.nextBytes(ivBytes);

        SecretKey key = new SecretKeySpec(keyBytes, "AES");
        GCMParameterSpec gcmSpec = new GCMParameterSpec(128, ivBytes);

        // Преобразуем IV в Base64 для передачи в ESAPI
        String ivBase64 = Base64.getEncoder().encodeToString(ivBytes);

        String encryptedData = encryptor.encrypt(sensitiveData, "AES/GCM/NoPadding", key, ivBase64);
        System.out.println("Encrypted with GCM (SECURE, integrity verified): " + encryptedData);
    }
}
