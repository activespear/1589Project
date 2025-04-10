package miem.projects.vulnerabilities.MAJOR.FB;

import java.nio.charset.StandardCharsets;

public class DM_DEFAULT_ENCODING {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        String str = "Hello, world!";

        // без явной кодировки
        byte[] bytes = str.getBytes();

        // без указания кодировки
        String decodedStr = new String(bytes);

        System.out.println(decodedStr);
    }

    public static void correctTest() {
        String str = "Hello, world!";

        // с явной кодировкой
        byte[] bytes = str.getBytes(StandardCharsets.UTF_8);

        // с явной кодировкой
        String decodedStr = new String(bytes, StandardCharsets.UTF_8);

        System.out.println(decodedStr);
    }
}
