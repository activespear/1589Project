package miem.projects.vulnerabilities.MINOR.FB;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class FB_IO_APPENDING_TO_OBJECT_OUTPUT_STREAM {

    public void writeObject(SomeObject obj) throws IOException {
        try (FileOutputStream fos = new FileOutputStream("file.txt", true);
             ObjectOutputStream out = new ObjectOutputStream(fos)) {
            out.writeObject(obj);
        }
    }

    public static class SomeObject implements java.io.Serializable {
        private static final long serialVersionUID = 1L;
    }
}
