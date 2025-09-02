package miem.projects.vulnerabilities.NORMAL;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PROC_USE_HASH_FUNC {
    public static void main(String[] args) throws Exception {
        byte[] data = "SensitiveData".getBytes();

        incorrectHash(data);
        correctHash(data);
    }

    // ❌ Неправильный пример: использование устаревшего MD5
    public static void incorrectHash(byte[] data) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5"); // Ненадёжный алгоритм
        byte[] hash = md.digest(data);
        System.out.println("MD5 hash: " + bytesToHex(hash));
    }

    // ✅ Правильный пример: использование современного алгоритма SHA-256
    public static void correctHash(byte[] data) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256"); // Безопасный алгоритм
        byte[] hash = md.digest(data);
        System.out.println("SHA-256 hash: " + bytesToHex(hash));
    }

    // Утилита для преобразования байтов в hex-строку
    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}
