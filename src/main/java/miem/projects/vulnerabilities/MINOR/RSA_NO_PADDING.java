package miem.projects.vulnerabilities.MINOR.FB;

import javax.crypto.Cipher;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PublicKey;
import java.security.PrivateKey;
import java.util.Base64;

public class FB_RSA_NO_PADDING {

    public void unsafe(byte[] data, PublicKey publicKey) throws Exception {
        // ❌ Потенциально небезопасное: RSA без паддинга уязвим к атакам
        Cipher cipher = Cipher.getInstance("RSA/ECB/NoPadding");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] encrypted = cipher.doFinal(data);

        System.out.println("Encrypted (insecure, NoPadding): " + Base64.getEncoder().encodeToString(encrypted));
    }

    public void safe(byte[] data, PublicKey publicKey, PrivateKey privateKey) throws Exception {
        // ✅ Корректная конструкция: RSA с OAEP (SHA-256 + MGF1)
        Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA-256AndMGF1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] encrypted = cipher.doFinal(data);

        System.out.println("Encrypted (secure, OAEP): " + Base64.getEncoder().encodeToString(encrypted));

        // 🔄 Для примера добавляем расшифровку
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] decrypted = cipher.doFinal(encrypted);

        System.out.println("Decrypted: " + new String(decrypted));
    }

    // Пример запуска
    public static void main(String[] args) throws Exception {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048);
        KeyPair keyPair = keyGen.generateKeyPair();

        FB_RSA_NO_PADDING demo = new FB_RSA_NO_PADDING();
        byte[] data = "SensitiveData123".getBytes();

        demo.unsafe(data, keyPair.getPublic());  // ❌ небезопасный вариант
        demo.safe(data, keyPair.getPublic(), keyPair.getPrivate()); // ✅ безопасный вариант
    }
}
