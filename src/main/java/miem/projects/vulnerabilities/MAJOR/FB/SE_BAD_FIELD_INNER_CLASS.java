package miem.projects.vulnerabilities.MAJOR.FB;

import java.io.*;

public class SE_BAD_FIELD_INNER_CLASS {

    // Небезопасный внешний класс с нестатическим внутренним классом
    static class OuterClassUnsafe {
        private String name = "OuterUnsafe";

        class InnerClass implements Serializable {
            private int id;

            public InnerClass(int id) {
                this.id = id;
            }
        }
    }

    // Безопасный внешний класс, реализующий Serializable,
    // с внутренним статическим сериализуемым классом
    static class OuterClassSafe implements Serializable {
        private String name = "OuterSafe";

        static class InnerClass implements Serializable {
            private int id;

            public InnerClass(int id) {
                this.id = id;
            }
        }
    }

    // Демонстрация небезопасной сериализации — выбросит NotSerializableException из-за OuterClassUnsafe$InnerClass
    static void unsafeSerialization() {
        OuterClassUnsafe outer = new OuterClassUnsafe();
        OuterClassUnsafe.InnerClass inner = outer.new InnerClass(1);

        try (ObjectOutputStream oos = new ObjectOutputStream(new ByteArrayOutputStream())) {
            oos.writeObject(inner);
            System.out.println("Unsafe serialization succeeded (unexpected).");
        } catch (NotSerializableException e) {
            System.out.println("Unsafe serialization failed: " + e);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Демонстрация безопасной сериализации — пройдёт успешно
    static void safeSerialization() {
        OuterClassSafe.InnerClass inner = new OuterClassSafe.InnerClass(2);

        try (ObjectOutputStream oos = new ObjectOutputStream(new ByteArrayOutputStream())) {
            oos.writeObject(inner);
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
