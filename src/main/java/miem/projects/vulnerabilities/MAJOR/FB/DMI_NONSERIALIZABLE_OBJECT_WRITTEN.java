package miem.projects.vulnerabilities.MAJOR.FB;

import java.io.*;

public class DMI_NONSERIALIZABLE_OBJECT_WRITTEN {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        // передача несериализуемого объекта в writeObject
        class NonSerializableClass {
            private int id;

            public NonSerializableClass(int id) {
                this.id = id;
            }

            public int getId() {
                return id;
            }
        }

        NonSerializableClass obj = new NonSerializableClass(1);

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("object.dat"))) {
            oos.writeObject(obj);  // Попытка сериализовать несериализуемый объект
        } catch (IOException e) {
            System.out.println("Ошибка при сериализации: " + e.getMessage());
        }
    }

    public static void correctTest() {
        // объект должен быть сериализуемым
        class SerializableClass implements Serializable {
            private int id;

            public SerializableClass(int id) {
                this.id = id;
            }

            public int getId() {
                return id;
            }
        }

        SerializableClass obj = new SerializableClass(1);

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("object.dat"))) {
            oos.writeObject(obj);  // Сериализация сериализуемого объекта
        } catch (IOException e) {
            System.out.println("Ошибка при сериализации: " + e.getMessage());
        }
    }
}