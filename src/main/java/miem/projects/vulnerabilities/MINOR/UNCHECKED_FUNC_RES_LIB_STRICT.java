package miem.projects.vulnerabilities.NORMAL;

import java.io.*;
import java.nio.channels.FileChannel;

public class UNCHECKED_FUNC_RES_LIB_STRICT {
    public static void main(String[] args) throws IOException {
        File src = new File("source.txt");
        File dst = new File("dest.txt");
        incorrectTest(src, dst);
        correctTest(src, dst);
    }

    public static void incorrectTest(File src, File dst) throws IOException {
        try (FileInputStream in = new FileInputStream(src);
             FileOutputStream out = new FileOutputStream(dst);
             FileChannel inChannel = in.getChannel();
             FileChannel outChannel = out.getChannel()) {
            inChannel.transferTo(0, inChannel.size(), outChannel);
        }
    }

    public static void correctTest(File src, File dst) throws IOException {
        try (FileInputStream in = new FileInputStream(src);
             FileOutputStream out = new FileOutputStream(dst);
             FileChannel inChannel = in.getChannel();
             FileChannel outChannel = out.getChannel()) {

            long transferred = inChannel.transferTo(0, inChannel.size(), outChannel);
            if (transferred != inChannel.size()) {
                throw new IOException("Failed to transfer entire file");
            }
        }
    }
}
