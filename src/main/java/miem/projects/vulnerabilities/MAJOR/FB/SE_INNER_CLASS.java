package miem.projects.vulnerabilities.MAJOR.FB;

import java.io.*;

public class SE_INNER_CLASS {

    // Небезопасная конструкция: нестатический внутренний класс Serializable
    static class OuterUnsafe implements Serializable {
        int outerValue = 10;

        class Inner implements Serializable {
            int innerValue = 5;
        }

        void unsafeSerialize() {
            Inner inner = new Inner();
            try (ObjectOutputStream oos = new ObjectOutputStream(new ByteArrayOutputStream())) {
                oos.writeObject(this);
                System.out.println("Unsafe serialization succeeded (unexpected).");
            } catch (NotSerializableException e) {
                System.out.println("Unsafe serialization failed as expected: " + e);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // Безопасная конструкция: статический внутренний класс Serializable
    static class OuterSafe implements Serializable {
        int outerValue = 10;

        static class Inner implements Serializable {
            int innerValue = 5;
        }

        void safeSerialize() {
            Inner inner = new Inner();
            try (ObjectOutputStream oos = new ObjectOutputStream(new ByteArrayOutputStream())) {
                oos.writeObject(this);
                System.out.println("Safe serialization succeeded.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Unsafe Serialization ===");
        OuterUnsafe outerUnsafe = new OuterUnsafe();
        outerUnsafe.unsafeSerialize();

        System.out.println("\n=== Safe Serialization ===");
        OuterSafe outerSafe = new OuterSafe();
        outerSafe.safeSerialize();
    }
}
