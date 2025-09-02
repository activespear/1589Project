package miem.projects.vulnerabilities.MINOR.FB;

import java.io.File;
import java.nio.file.Paths;
import java.util.UUID;
import org.apache.commons.fileupload.FileItem;

public class FB_FILE_UPLOAD_FILENAME {

    // Потенциально небезопасное использование имени файла
    public static void unsafeUpload(FileItem fileItem) throws Exception {
        File file = new File("/uploads/" + fileItem.getName());
        fileItem.write(file);
        System.out.println("Unsafe file saved: " + file.getAbsolutePath());
    }

    // Корректная конструкция с проверкой имени и безопасным генератором
    public static void safeUpload(FileItem fileItem) throws Exception {
        String originalName = Paths.get(fileItem.getName()).getFileName().toString();

        // Проверка валидности имени файла
        if (!originalName.matches("^[\\w,\\s-]+\\.[A-Za-z]{3,4}$")) {
            throw new IllegalArgumentException("Invalid file name");
        }

        // Генерация безопасного уникального имени
        String safeName = UUID.randomUUID().toString() + "_" + originalName;
        File file = new File("/uploads/" + safeName);
        fileItem.write(file);
        System.out.println("Safe file saved: " + file.getAbsolutePath());
    }

    public static void main(String[] args) throws Exception {
        // Здесь fileItem должен быть получен из реального запроса на загрузку
        // Для демонстрации можно создать мок или использовать существующий объект
    }
}
