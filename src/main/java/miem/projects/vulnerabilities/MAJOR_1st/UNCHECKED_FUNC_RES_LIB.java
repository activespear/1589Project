package miem.projects.vulnerabilities.MAJOR_1st;

import java.io.File;

public class UNCHECKED_FUNC_RES_LIB {

    // Небезопасная конструкция: не проверяет существование файла и результат переименования
    public static void unsafeRename(String oldpath, String newpath) {
        File file = new File(oldpath);
        boolean success = file.renameTo(new File(newpath));
        System.out.println("Unsafe rename success: " + success);
    }

    // Немного безопаснее — проверяет только результат, но не существование файла
    public static void possibleFixRename(String oldpath, String newpath) {
        File file = new File(oldpath);
        if (file.renameTo(new File(newpath))) {
            System.out.println("Possible fix rename success");
        } else {
            System.out.println("Possible fix rename failed");
        }
    }

    // Безопасная конструкция: проверяет существование файла и результат операции
    public static void safeRename(String oldpath, String newpath) {
        File file = new File(oldpath);
        if (file.exists()) {
            boolean success = file.renameTo(new File(newpath));
            if (success) {
                System.out.println("Safe rename success");
            } else {
                System.out.println("Safe rename failed");
                // Обработка ошибки переименования
            }
        } else {
            System.out.println("File does not exist: " + oldpath);
            // Обработка случая, если файл не существует
        }
    }

    public static void main(String[] args) {
        String oldPath = "test_old.txt";
        String newPath = "test_new.txt";

        System.out.println("Running unsafeRename:");
        unsafeRename(oldPath, newPath);

        System.out.println("\nRunning possibleFixRename:");
        possibleFixRename(oldPath, newPath);

        System.out.println("\nRunning safeRename:");
        safeRename(oldPath, newPath);
    }
}

