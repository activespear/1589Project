package miem.projects.vulnerabilities.MAJOR.FB;

import java.io.*;

public class RS_READOBJECT_SYNC {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        class Test implements Serializable {
            @Serial
            private static final long serialVersionUID = 1L;

            @Serial
            private synchronized void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
                // ненужная синхронизация
                stream.defaultReadObject();
            }
        }
        testSerialization(new Test());
    }

    public static void correctTest() {
        class Test implements Serializable {
            @Serial
            private static final long serialVersionUID = 1L;

            @Serial
            private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
                ois.defaultReadObject();
            }
        }
        testSerialization(new Test());
    }

    private static void testSerialization(Serializable obj) {
        try {
            // Сериализация
            ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(byteOut);
            out.writeObject(obj);
            out.close();

            // Десериализация
            ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
            ObjectInputStream in = new ObjectInputStream(byteIn);
            Object deserializedObj = in.readObject();
            in.close();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
