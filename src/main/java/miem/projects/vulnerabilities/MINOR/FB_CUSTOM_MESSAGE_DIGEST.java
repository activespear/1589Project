package miem.projects.vulnerabilities.MINOR.FB;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class FB_CUSTOM_MESSAGE_DIGEST {

    // Потенциально небезопасное кастомное MessageDigest
    public static class MyDigest extends MessageDigest {

        protected MyDigest() {
            super("MyDigest");
        }

        @Override
        protected void engineUpdate(byte input) {
            // кастомная логика (может быть небезопасной)
        }

        @Override
        protected byte[] engineDigest() {
            return new byte[0]; // небезопасная кастомная реализация
        }

        @Override
        protected void engineReset() {}
    }

    // Корректная конструкция с SHA-256
    public static byte[] safeDigest(String input) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        return digest.digest(input.getBytes(StandardCharsets.UTF_8));
    }

    public static void main(String[] args) throws Exception {
        String input = "mySecureData";

        // Unsafe custom digest
        MyDigest unsafe = new MyDigest();
        unsafe.update((byte) 0); // пример вызова
        byte[] unsafeHash = unsafe.digest();
        System.out.println("Unsafe custom digest: " + unsafeHash.length + " bytes");

        // Safe SHA-256 digest
        byte[] safeHash = safeDigest(input);
        System.out.println("Safe SHA-256 digest: " + bytesToHex(safeHash));
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}
