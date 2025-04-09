package miem.projects.vulnerabilities.MINOR.FB;

import java.security.AccessController;
import java.security.PrivilegedAction;
import java.io.File;

public class DP_DO_INSIDE_DO_PRIVILEGED {

    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    // Проблемные примеры
    public static void incorrectTest() {
        // 1. Избыточные операции в doPrivileged
        AccessController.doPrivileged((PrivilegedAction<Void>) () -> {
            String userDir = System.getProperty("user.dir"); // Требует прав
            System.out.println("Log: " + userDir); // НЕ требует прав!
            return null;
        });

        // 2. Смешанные привилегированные операции
        AccessController.doPrivileged((PrivilegedAction<Void>) () -> {
            File f = new File("/secure/data.bin"); // Проверка пути - безопасна
            if (f.exists()) {                      // Проверка существования - требует прав!
                f.delete();                        // Критическая операция
            }
            return null;
        });
    }

    // Корректные аналоги
    public static void correctTest() {
        // 1. Минимизация привилегированного блока
        String userDir = AccessController.doPrivileged(
            (PrivilegedAction<String>) () -> System.getProperty("user.dir"));
        System.out.println("Log: " + userDir); // Вне привилегированного блока

        // 2. Разделение проверок и операций
        File f = new File("/secure/data.bin");
        boolean exists = AccessController.doPrivileged(
            (PrivilegedAction<Boolean>) f::exists);
        
        if (exists) {
            AccessController.doPrivileged(
                (PrivilegedAction<Void>) () -> {
                    f.delete();
                    return null;
                });
        }
    }
}