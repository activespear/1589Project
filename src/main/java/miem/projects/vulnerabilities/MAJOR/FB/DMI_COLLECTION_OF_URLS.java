package miem.projects.vulnerabilities.MAJOR.FB;

import java.net.URL;
import java.util.HashSet;
import java.util.Set;

public class DMI_COLLECTION_OF_URLS {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        try {
            Set<URL> urlSet = new HashSet<>();
            urlSet.add(new URL("http://example.com"));
            urlSet.add(new URL("http://example.com"));

            // equals и hashCode для URL могут выполнять разрешение доменных имен
            System.out.println(urlSet);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void correctTest() {
        try {
            Set<CustomURL> urlSet = new HashSet<>();
            URL url1 = new URL("http://example.com");
            URL url2 = new URL("http://example.com");

            // Создание собственных объектов с кастомными equals и hashCode
            urlSet.add(new CustomURL(url1));
            urlSet.add(new CustomURL(url2));

            System.out.println(urlSet);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // Класс, оборачивающий URL и предоставляющий собственные реализации equals и hashCode
    public static class CustomURL {
        private final String protocol;
        private final String host;
        private final int port;

        public CustomURL(URL url) {
            this.protocol = url.getProtocol();
            this.host = url.getHost();
            this.port = url.getPort();
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            CustomURL customURL = (CustomURL) obj;
            return port == customURL.port &&
                    protocol.equals(customURL.protocol) &&
                    host.equals(customURL.host);
        }

        @Override
        public int hashCode() {
            return 31 * protocol.hashCode() + 31 * host.hashCode() + port;
        }

        @Override
        public String toString() {
            return protocol + "://" + host + ":" + port;
        }
    }
}
