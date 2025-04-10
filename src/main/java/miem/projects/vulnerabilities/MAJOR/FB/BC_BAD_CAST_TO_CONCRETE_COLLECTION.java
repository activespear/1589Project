package miem.projects.vulnerabilities.MAJOR.FB;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

public class BC_BAD_CAST_TO_CONCRETE_COLLECTION {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        class MyClass {
            public void processCollection(Collection<?> collection) {
                // Неправильное приведение к конкретной реализации
                // Это может привести к ClassCastException, если collection не является ArrayList
                ArrayList<?> arrayList = (ArrayList<?>) collection;
                for (Object item : arrayList) {
                    System.out.println(item);
                }
            }
        }

        Collection<String> collection = new HashSet<>();
        collection.add("apple");
        collection.add("banana");

        MyClass myClass = new MyClass();
        myClass.processCollection(collection);
    }

    public static void correctTest() {
        class MyClass {
            public void processCollection(Collection<?> collection) {
                // Использование абстрактного типа для работы с коллекцией
                // Это более гибко и безопасно
                for (Object item : collection) {
                    System.out.println(item);
                }
            }
        }

        Collection<String> collection = new HashSet<>();
        collection.add("apple");
        collection.add("banana");

        MyClass myClass = new MyClass();
        myClass.processCollection(collection);
    }
}

