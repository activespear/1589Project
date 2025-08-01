package miem.projects.vulnerabilities.MAJOR.FB;

import java.io.*;

public class SE_TRANSIENT_FIELD_NOT_RESTORED {

    // Небезопасная конструкция: транзиентное поле не восстанавливается после десериализации
    static class CounterUnsafe implements Serializable {
        private static final long serialVersionUID = 1L;

        private int count;
        private transient String status;

        public CounterUnsafe() {
            this.status = "Initialized";
        }

        public void increment() {
            count++;
            status = "Updated";
        }

        public String getStatus() {
            return status;
        }

        @Override
        public String toString() {
            return "CounterUnsafe{count=" + count + ", status='" + status + "'}";
        }
    }

    // Безопасная конструкция: метод readObject восстанавливает транзиентное поле
    static class CounterSafe implements Serializable {
        private static final long serialVersionUID = 1L;

        private int count;
        private transient String status;

        public CounterSafe() {
            this.status = "Initialized";
        }

        public void increment() {
            count++;
            status = "Updated";
        }

        public String getStatus() {
            return status;
        }

        private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
            in.defaultReadObject();
            status = "Deserialized";
        }

        @Override
        public String toString() {
            return "CounterSafe{count=" + count + ", status='" + status + "'}";
        }
    }

    // Функция для сериализации объекта в байтовый массив
    private static byte[] serialize(Object obj) throws IOException {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             ObjectOutputStream out = new ObjectOutputStream(baos)) {
            out.writeObject(obj);
            return baos.toByteArray();
        }
    }

    // Функция для десериализации объекта из байтового массива
    private static Object deserialize(byte[] data) throws IOException, ClassNotFoundException {
        try (ByteArrayInputStream bais = new ByteArrayInputStream(data);
             ObjectInputStream in = new ObjectInputStream(bais)) {
            return in.readObject();
        }
    }

    // Тест небезопасной конструкции
    public static void unsafeTest() throws IOException, ClassNotFoundException {
        CounterUnsafe counter = new CounterUnsafe();
        counter.increment();

        System.out.println("Before serialization (unsafe): " + counter);

        byte[] data = serialize(counter);
        CounterUnsafe deserialized = (CounterUnsafe) deserialize(data);

        System.out.println("After deserialization (unsafe): " + deserialized);
        System.out.println("Status after deserialization (unsafe): " + deserialized.getStatus());
    }

    // Тест безопасной конструкции
    public static void safeTest() throws IOException, ClassNotFoundException {
        CounterSafe counter = new CounterSafe();
        counter.increment();

        System.out.println("Before serialization (safe): " + counter);

        byte[] data = serialize(counter);
        CounterSafe deserialized = (CounterSafe) deserialize(data);

        System.out.println("After deserialization (safe): " + deserialized);
        System.out.println("Status after deserialization (safe): " + deserialized.getStatus());
    }

    public static void main(String[] args) throws Exception {
        System.out.println("=== Unsafe test ===");
        unsafeTest();

        System.out.println("\n=== Safe test ===");
        safeTest();
    }
}
