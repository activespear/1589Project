package miem.projects.vulnerabilities.MAJOR.FB;

import java.io.*;

public class SE_NO_SERIALVERSIONID {

    // Небезопасная конструкция: Serializable класс без serialVersionUID
    static class UserUnsafe implements Serializable {
        private String name;
        private int age;

        UserUnsafe(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return "UserUnsafe{name='" + name + "', age=" + age + "}";
        }
    }

    // Безопасная конструкция: Serializable класс с serialVersionUID
    static class UserSafe implements Serializable {
        private static final long serialVersionUID = 1L;
        private String name;
        private int age;

        UserSafe(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return "UserSafe{name='" + name + "', age=" + age + "}";
        }
    }

    public static void unsafeSerialization() {
        UserUnsafe user = new UserUnsafe("Alice", 30);
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(baos)) {

            oos.writeObject(user);
            System.out.println("Unsafe serialization succeeded: " + user);

            // Для демонстрации десериализации
            try (ObjectInputStream ois = new ObjectInputStream(
                    new ByteArrayInputStream(baos.toByteArray()))) {
                UserUnsafe deserializedUser = (UserUnsafe) ois.readObject();
                System.out.println("Unsafe deserialization succeeded: " + deserializedUser);
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void safeSerialization() {
        UserSafe user = new UserSafe("Bob", 25);
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(baos)) {

            oos.writeObject(user);
            System.out.println("Safe serialization succeeded: " + user);

            try (ObjectInputStream ois = new ObjectInputStream(
                    new ByteArrayInputStream(baos.toByteArray()))) {
                UserSafe deserializedUser = (UserSafe) ois.readObject();
                System.out.println("Safe deserialization succeeded: " + deserializedUser);
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Unsafe User (no serialVersionUID) ===");
        unsafeSerialization();

        System.out.println("\n=== Safe User (with serialVersionUID) ===");
        safeSerialization();
    }
}

