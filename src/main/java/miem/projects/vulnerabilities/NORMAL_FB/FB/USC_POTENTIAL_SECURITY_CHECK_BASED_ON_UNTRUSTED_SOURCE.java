package miem.projects.vulnerabilities.NORMAL.FB;

import java.security.*;
import java.util.function.*;

public class USC_POTENTIAL_SECURITY_CHECK_BASED_ON_UNTRUSTED_SOURCE {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    // вспомогательный класс для примера
    static class FileInfo {
        private final String path;
        public FileInfo(String path) { this.path = path; }
        public FileInfo(FileInfo other) { this.path = other.path; }
        public String getPath() { return path; }
    }

    static boolean isSafePath(String path) {
        return path != null && path.startsWith("/safe/");
    }

    static void processInternal(FileInfo fileInfo) {
        System.out.println("Processing file: " + fileInfo.getPath());
    }

    public static void incorrectTest() {
        class FileProcessor {
            public void processFile(FileInfo fileInfo) {
                if (isSafePath(fileInfo.getPath())) {
                    AccessController.doPrivileged((PrivilegedAction<Void>) () -> {
                        processInternal(fileInfo);
                        return null;
                    });
                }
            }
        }

        FileProcessor processor = new FileProcessor();
        processor.processFile(new FileInfo("/safe/data.txt"));
    }

    public static void correctTest() {
        class SecureFileProcessor {
            public void processFile(FileInfo fileInfo) {
                FileInfo copy = new FileInfo(fileInfo);
                if (isSafePath(copy.getPath())) {
                    AccessController.doPrivileged((PrivilegedAction<Void>) () -> {
                        processInternal(copy);
                        return null;
                    });
                }
            }
        }

        SecureFileProcessor processor = new SecureFileProcessor();
        processor.processFile(new FileInfo("/safe/data.txt"));
    }
}
