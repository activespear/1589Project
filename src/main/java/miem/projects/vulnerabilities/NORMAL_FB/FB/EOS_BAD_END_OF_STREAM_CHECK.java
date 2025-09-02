package miem.projects.vulnerabilities.NORMAL.FB;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class EOS_BAD_END_OF_STREAM_CHECK {
    public static void main(String[] args) throws IOException {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() throws IOException {
        InputStream inputStream = new ByteArrayInputStream(new byte[]{0, 1, (byte) 255});
        byte b;
        while ((b = (byte) inputStream.read()) != -1) {
            process(b);
        }
    }

    public static void correctTest() throws IOException {
        InputStream inputStream = new ByteArrayInputStream(new byte[]{0, 1, (byte) 255});
        int data;
        while ((data = inputStream.read()) != -1) {
            byte b = (byte) data;
            process(b);
        }
    }

    private static void process(byte b) {
        System.out.println(b);
    }
}