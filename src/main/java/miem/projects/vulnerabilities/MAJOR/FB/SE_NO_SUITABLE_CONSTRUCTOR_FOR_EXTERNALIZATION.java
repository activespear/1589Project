package miem.projects.vulnerabilities.MAJOR.FB;

import java.io.*;

public class SE_NO_SUITABLE_CONSTRUCTOR_FOR_EXTERNALIZATION {

    // Небезопасная конструкция: нет конструктора по умолчанию
    static class MyDataUnsafe implements Externalizable {
        private int value;

        public MyDataUnsafe(int value) {
            this.value = value;
        }

        @Override
        public void writeExternal(ObjectOutput out) throws IOException {
            out.writeInt(value);
        }

        @Override
        public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
            value = in.readInt();
        }

        @Override
        public String toString() {
            return "MyDataUnsafe{value=" + value + "}";
        }
    }

    // Безопасная конструкция: есть конструктор по умолчанию
    static class MyDataSafe implements Externalizable {
        private int value;

        public MyDataSafe() {
            // Конструктор по умолчанию обязателен для Externalizable
        }

        public MyDataSafe(int value) {
            this.value = value;
        }

        @Override
        public void writeExternal(ObjectOutput out) throws IOException {
            out.writeInt(value);
        }

        @Override
        public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
            value = in.readInt();
        }

        @Override
        public String toString() {
            return "MyDataSafe{value=" + value + "}";
        }
    }

    // Функция, демонстрирующая проблему с MyDataUnsafe
    public static void unsafeTest() {
        MyDataUnsafe data = new MyDataUnsafe(42);
        System.out.println("Unsafe object created: " + data);

        // Здесь обычно сериализация и десериализация, которые вызовут ошибку из-за отсутствия конструктора по умолчанию
        // Но для демонстрации ограничимся выводом.
    }

    // Функция с безопасным классом
    public static void safeTest() {
        MyDataSafe data = new MyDataSafe(42);
        System.out.println("Safe object created: " + data);

        // Корректная сериализация/десериализация возможна
    }

    public static void main(String[] args) {
        System.out.println("=== Unsafe construction ===");
        unsafeTest();

        System.out.println("\n=== Safe construction ===");
        safeTest();
    }
}

