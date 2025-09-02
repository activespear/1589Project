package miem.projects.vulnerabilities.MINOR.FB;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.util.Base64;

public class FB_DES_USAGE {

    public void unsafe(byte[] data, javax.crypto.SecretKey secretKey) throws Exception {
        // ❌ Потенциально небезопасное: DES устарел и легко взламывается
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encrypted = cipher.doFinal(data);
        System.out.println("Encrypted (DES, insecure): " + Base64.getEncoder().encodeToString(encrypted));
    }

    public void safe(byte[] data, byte[] keyBytes) throws Exception {
        // ✅ Корректная конструкция: AES с CBC и PKCS5Padding
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

        // Генерация безопасного IV
        byte[] ivBytes = new byte[16];
        new SecureRandom().nextBytes(ivBytes);

        SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");
        IvParameterSpec iv = new IvParameterSpec(ivBytes);

        cipher.init(Cipher.ENCRYPT_MODE, keySpec, iv);
        byte[] encrypted = cipher.doFinal(data);

        System.out.println("Encrypted (AES): " + Base64.getEncoder().encodeToString(encrypted));
    }
}
