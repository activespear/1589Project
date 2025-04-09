package miem.projects.vulnerabilities.MINOR.FB;

import java.io.*;

public class FB_WS_WRITEOBJECT_SYNC {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        class SyncSerializable implements Serializable {
            private final Object lock = new Object();
            private int value;

            private void writeObject(ObjectOutputStream out) throws IOException {
                synchronized (lock) {  // Опасная синхронизация при сериализации
                    out.defaultWriteObject();
                }
            }
        }

        SyncSerializable obj = new SyncSerializable();
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(bos)) {
            oos.writeObject(obj);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void correctTest() {
        class SafeSerializable implements Serializable {
            private transient final Object lock = new Object();  // transient для несериализуемого поля
            private int value;

            private void writeObject(ObjectOutputStream out) throws IOException {
                out.defaultWriteObject();  // Без синхронизации
            }
        }

        SafeSerializable obj = new SafeSerializable();
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(bos)) {
            oos.writeObject(obj);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}