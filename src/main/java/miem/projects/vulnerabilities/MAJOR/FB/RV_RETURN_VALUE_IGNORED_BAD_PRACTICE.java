package miem.projects.vulnerabilities.MAJOR.FB;

import java.io.File;

public class RV_RETURN_VALUE_IGNORED_BAD_PRACTICE {

    // Небезопасное использование — игнорируем результат delete()
    static void unsafeDelete(File file) {
        file.delete();  // результат игнорируется
        System.out.println("Unsafe: попытка удаления файла без проверки результата.");
    }

    // Безопасное использование — проверяем результат delete()
    static void safeDelete(File file) {
        boolean deleted = file.delete();
        if (!deleted) {
            System.out.println("Safe: Ошибка — не удалось удалить файл: " + file.getPath());
        } else {
            System.out.println("Safe: Файл успешно удалён: " + file.getPath());
        }
    }

    public static void main(String[] args) {
        // Для демонстрации создадим временный файл
        try {
            File tempFile = File.createTempFile("tempfile", ".tmp");
            System.out.println("Создан временный файл: " + tempFile.getPath());

            System.out.println("\nЗапуск unsafeDelete:");
            unsafeDelete(tempFile);

            // Попытка удаления повторно (файл скорее всего уже удалён)
            System.out.println("\nЗапуск safeDelete:");
            safeDelete(tempFile);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

