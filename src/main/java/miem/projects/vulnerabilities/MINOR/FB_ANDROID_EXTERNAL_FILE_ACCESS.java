package miem.projects.vulnerabilities.MINOR.FB;

import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class FB_ANDROID_EXTERNAL_FILE_ACCESS {

    public static void incorrectTest(String userSensitiveData) throws Exception {
        // Некорректно: запись на внешний публичный каталог, данные доступны другим приложениям
        File file = new File(Environment.getExternalStorageDirectory(), "sensitiveData.txt");
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(userSensitiveData.getBytes());
        }
        System.out.println("Written to public external storage (INSECURE)");
    }

    public static void correctTest(Context context, String userSensitiveData) throws Exception {
        // Корректно: использование приватного каталога приложения и шифрование данных
        File file = new File(context.getExternalFilesDir(null), "sensitiveData.txt");

        byte[] encryptedData = encrypt(userSensitiveData);

        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(encryptedData);
        }
        System.out.println("Written to app-private external storage with encryption (SECURE)");
    }

    // Пример простого шифрования AES/GCM
    private static byte[] encrypt(String data) throws Exception {
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
        byte[] result = new byte[iv.length + ciphertext.length];
        System.arraycopy(iv, 0, result, 0, iv.length);
        System.arraycopy(ciphertext, 0, result, iv.length, ciphertext.length);

        return result;
    }
}
