package miem.projects.vulnerabilities.MINOR.FB;

import android.content.Context;
import android.content.Intent;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;

public class FB_ANDROID_BROADCAST {

    public static void incorrectTest(Context context) {
        // Некорректно: отправка пароля в открытом виде через широковещательный Intent
        Intent intent = new Intent("com.example.broadcast");
        intent.putExtra("userPassword", "12345");
        context.sendBroadcast(intent);
        System.out.println("Broadcast sent with plaintext password (INSECURE)");
    }

    public static void correctTest(Context context) throws Exception {
        // Корректно: шифрование данных перед отправкой
        String encryptedPassword = encrypt("12345");

        Intent intent = new Intent("com.example.broadcast");
        intent.putExtra("userPassword", encryptedPassword);
        context.sendBroadcast(intent);
        System.out.println("Broadcast sent with encrypted password (SECURE)");
    }

    // Пример простого шифрования AES/GCM
    private static String encrypt(String data) throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(128);
        SecretKey key = keyGen.generateKey();

        byte[] iv = new byte[12];
        SecureRandom random = new SecureRandom();
        random.nextBytes(iv);

        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        cipher.init(Cipher.ENCRYPT_MODE, key, new GCMParameterSpec(128, iv));

        byte[] ciphertext = cipher.doFinal(data.getBytes());

        // Объединяем IV и шифртекст для хранения
        byte[] combined = new byte[iv.length + ciphertext.length];
        System.arraycopy(iv, 0, combined, 0, iv.length);
        System.arraycopy(ciphertext, 0, combined, iv.length, ciphertext.length);

        return java.util.Base64.getEncoder().encodeToString(combined);
    }
}
