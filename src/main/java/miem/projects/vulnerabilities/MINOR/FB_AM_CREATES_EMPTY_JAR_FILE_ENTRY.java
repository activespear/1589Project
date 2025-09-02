package miem.projects.vulnerabilities.MINOR.FB;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.jar.JarEntry;
import java.util.jar.JarOutputStream;

public class FB_AM_CREATES_EMPTY_JAR_FILE_ENTRY {

    public static void main(String[] args) throws IOException {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() throws IOException {
        JarOutputStream jarOut = new JarOutputStream(new FileOutputStream("example_empty.jar"));
        JarEntry entry = new JarEntry("MyClass.class");
        jarOut.putNextEntry(entry);
        jarOut.closeEntry(); // Пустой файл без данных
        jarOut.close();
        System.out.println("Created empty JAR entry (INSECURE)");
    }

    public static void correctTest() throws IOException {
        JarOutputStream jarOut = new JarOutputStream(new FileOutputStream("example_filled.jar"));
        JarEntry entry = new JarEntry("MyClass.class");
        jarOut.putNextEntry(entry);

        // Запись содержимого файла
        byte[] content = Files.readAllBytes(Paths.get("build/classes/MyClass.class"));
        jarOut.write(content);

        jarOut.closeEntry(); // Закрытие с содержимым
        jarOut.close();
        System.out.println("Created JAR entry with content (SECURE)");
    }
}
