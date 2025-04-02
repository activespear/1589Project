package miem.projects.vulnerabilities.MAJOR.FB;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

public class BC_BAD_CAST_TO_ABSTRACT_COLLECTION {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        class MyClass {
            public void processCollection(Collection<?> collection) {
                // Неправильное приведение коллекции к абстрактному типу
                // Это может привести к ClassCastException, если collection не является List
                List<?> list = (List<?>) collection;
                for (Object item : list) {
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
                // Просто перебирать коллекцию без приведения ее к абстрактному типу
                // Это более безопасно, так как не зависит от типа коллекции
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