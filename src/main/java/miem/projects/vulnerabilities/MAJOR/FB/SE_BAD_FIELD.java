package miem.projects.vulnerabilities.MAJOR.FB;

import java.io.*;

public class SE_BAD_FIELD {

    // Небезопасный класс с несериализуемым полем без transient
    static class MyClassUnsafe implements Serializable {
        private String name;
        private MyNonSerializableClass nonSerializableField;

        public MyClassUnsafe(String name, MyNonSerializableClass nonSerializableField) {
            this.name = name;
            this.nonSerializableField = nonSerializableField;
        }
    }

    // Безопасный класс с transient полем
    static class MyClassSafe implements Serializable {
        private String name;
        private transient MyNonSerializableClass nonSerializableField;

        public MyClassSafe(String name, MyNonSerializableClass nonSerializableField) {
            this.name = name;
            this.nonSerializableField = nonSerializableField;
        }
    }

    // Несериализуемый класс
    static class MyNonSerializableClass {
        private int data = 42;
    }

    // Демонстрация небезопасной сериализации — должно выбросить NotSerializableException
    static void unsafeSerialization() {
        MyClassUnsafe obj = new MyClassUnsafe("TestUnsafe", new MyNonSerializableClass());

        try (ObjectOutputStream oos = new ObjectOutputStream(new ByteArrayOutputStream())) {
            oos.writeObject(obj);
            System.out.println("Unsafe serialization succeeded (unexpected).");
        } catch (NotSerializableException e) {
            System.out.println("Unsafe serialization failed: " + e);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Демонстрация безопасной сериализации — transient поле игнорируется, сериализация успешна
    static void safeSerialization() {
        MyClassSafe obj = new MyClassSafe("TestSafe", new MyNonSerializableClass());

        try (ObjectOutputStream oos = new ObjectOutputStream(new ByteArrayOutputStream())) {
            oos.writeObject(obj);
            System.out.println("Safe serialization succeeded.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Unsafe Serialization ===");
        unsafeSerialization();

        System.out.println("\n=== Safe Serialization ===");
        safeSerialization();
    }
}

