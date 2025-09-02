package miem.projects.vulnerabilities.MINOR.FB;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.util.Base64;

public class FB_WEAK_MESSAGE_DIGEST {

    // Потенциально небезопасное использование MD5
    public static byte[] unsafeHash(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        return md.digest(password.getBytes(StandardCharsets.UTF_8));
    }

    // Корректное безопасное хэширование с PBKDF2
    public static byte[] safeHash(String password, byte[] salt) throws Exception {
        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 256);
        SecretKey key = skf.generateSecret(spec);
        return key.getEncoded();
    }

    public static void main(String[] args) throws Exception {
        String password = "mySecurePassword";

        // Unsafe MD5
        byte[] unsafe = unsafeHash(password);
        System.out.println("Unsafe MD5: " + Base64.getEncoder().encodeToString(unsafe));

        // Safe PBKDF2
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        byte[] safe = safeHash(password, salt);
        System.out.println("Safe PBKDF2: " + Base64.getEncoder().encodeToString(safe));
    }
}
