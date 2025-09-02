package miem.projects.vulnerabilities.NORMAL;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class HANDLE_LEAK_EX_EXCEPTION {

    public static void main(String[] args) throws IOException {
        incorrectTest("Hello", true);
        correctTest("Hello", true);
    }

    public static void incorrectTest(String source, boolean isFile) throws IOException {
        InputStream stream;
        if (isFile) {
            stream = new FileInputStream(source);
        } else {
            stream = new ByteArrayInputStream(source.getBytes(StandardCharsets.UTF_8));
        }
        System.out.println(stream.read());
        if (isFile) {
            stream.close();
        }
    }

    public static void correctTest(String source, boolean isFile) throws IOException {
        try (InputStream stream = isFile
                ? new FileInputStream(source)
                : new ByteArrayInputStream(source.getBytes(StandardCharsets.UTF_8))) {
            System.out.println(stream.read());
        }
    }
}
