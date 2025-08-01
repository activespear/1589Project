package miem.projects.vulnerabilities.MAJOR.FB;

import java.io.*;

public class SE_BAD_FIELD_STORE {

    // Небезопасная версия — поле nonSerializableField не transient, сериализация упадет
    static class SerializableClassUnsafe implements Serializable {
        private String name;
        private NonSerializableClass nonSerializableField;

        public SerializableClassUnsafe(String name, NonSerializableClass nonSerializableField) {
            this.name = name;
            this.nonSerializableField = nonSerializableField;
        }
    }

    // Безопасная версия — поле nonSerializableField объявлено transient
    static class SerializableClassSafe implements Serializable {
        private String name;
        private transient NonSerializableClass nonSerializableField;

        public SerializableClassSafe(String name, NonSerializableClass nonSerializableField) {
            this.name = name;
            this.nonSerializableField = nonSerializableField;
        }
    }

    // Класс, не реализующий Serializable
    static class NonSerializableClass {
        private int id;

        public NonSerializableClass(int id) {
            this.id = id;
        }
    }

    // Попытка сериализовать небезопасный объект — ожидаем ошибку
    static void unsafeSerialization() {
        SerializableClassUnsafe obj = new SerializableClassUnsafe("Test", new NonSerializableClass(123));
        try (ObjectOutputStream oos = new ObjectOutputStream(new ByteArrayOutputStream())) {
            oos.writeObject(obj);
            System.out.println("Unsafe serialization succeeded (unexpected).");
        } catch (NotSerializableException e) {
            System.out.println("Unsafe serialization failed: " + e);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Сериализация безопасного объекта — проходит успешно
    static void safeSerialization() {
        SerializableClassSafe obj = new SerializableClassSafe("Test", new NonSerializableClass(456));
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
