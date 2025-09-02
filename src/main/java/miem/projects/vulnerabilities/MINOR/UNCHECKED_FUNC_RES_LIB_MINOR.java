package miem.projects.vulnerabilities.MINOR;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class UNCHECKED_FUNC_RES_LIB_MINOR {
    public static void main(String[] args) throws IOException {
        File src = new File("source.txt");
        File dst = new File("dest.txt");
        incorrectTest(src, dst);
        correctTest(src, dst);
    }

    public static void incorrectTest(File src, File dst) throws IOException {
        long bytesCopied = Files.copy(src.toPath(), dst.toPath());
        System.out.println("File copied, but success is not verified");
    }

    public static void correctTest(File src, File dst) throws IOException {
        long bytesCopied = Files.copy(src.toPath(), dst.toPath());
        if (bytesCopied != src.length()) {
            throw new IOException("File copy failed: incomplete transfer");
        }
        System.out.println("File copied successfully");
    }
}
