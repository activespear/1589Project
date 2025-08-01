package miem.projects.vulnerabilities.MAJOR.FB;

import java.io.*;
import java.util.*;

public class SE_COMPARATOR_SHOULD_BE_SERIALIZABLE {

// Небезопасный компаратор — не реализует Serializable
static class NonSerializableComparator implements Comparator<String> {
    @Override
    public int compare(String o1, String o2) {
        return o1.compareTo(o2);
    }
}

// Безопасный компаратор — реализует Serializable
static class SerializableComparator implements Comparator<String>, Serializable {
    private static final long serialVersionUID = 1L;

    @Override
    public int compare(String o1, String o2) {
        return o1.compareTo(o2);
    }
}

// Метод демонстрирующий небезопасную сериализацию (ожидаем NotSerializableException)
static void unsafeSerialization() {
    TreeMap<String, Integer> map = new TreeMap<>(new NonSerializableComparator());
    map.put("one", 1);
    map.put("two", 2);
    try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("unsafe_map.ser"))) {
        out.writeObject(map);
        System.out.println("Unsafe serialization succeeded (unexpected).");
    } catch (NotSerializableException e) {
        System.out.println("Unsafe serialization failed as expected: " + e);
    } catch (IOException e) {
        e.printStackTrace();
    }
}

// Метод демонстрирующий безопасную сериализацию
static void safeSerialization() {
    TreeMap<String, Integer> map = new TreeMap<>(new SerializableComparator());
    map.put("one", 1);
    map.put("two", 2);
    try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("safe_map.ser"))) {
        out.writeObject(map);
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
