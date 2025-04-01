package miem.projects.vulnerabilities.MAJOR.FB;

import java.net.URL;
import java.util.Objects;

public class DMI_BLOCKING_METHODS_ON_URL {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        try {
            URL url1 = new URL("http://example.com");
            URL url2 = new URL("http://example.com");
            // метод может блокировать выполнение
            System.out.println(url1.equals(url2));
            // hashCode может также выполнять сетевые операции
            System.out.println(url1.hashCode());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void correctTest() {
        try {
            URL url1 = new URL("http://example.com");
            URL url2 = new URL("http://example.com");

            System.out.println(customEquals(url1, url2));

            System.out.println(customHashCode(url1));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // Собственная реализация equals, которая не вызывает сетевые операции
    public static boolean customEquals(URL url1, URL url2) {
        return url1.getProtocol().equals(url2.getProtocol()) &&
                url1.getHost().equals(url2.getHost()) &&
                url1.getPort() == url2.getPort();
    }

    // Собственная реализация hashCode, которая не вызывает сетевые операции
    public static int customHashCode(URL url) {
        return Objects.hash(url.getProtocol(), url.getHost(), url.getPort());
    }
}
