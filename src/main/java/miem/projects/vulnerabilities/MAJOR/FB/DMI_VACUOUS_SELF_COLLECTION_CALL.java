package miem.projects.vulnerabilities.MAJOR.FB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DMI_VACUOUS_SELF_COLLECTION_CALL {

    // Небезопасная конструкция — вызовы на самой себе
    public static void unsafeOperations() {
        List<String> list = new ArrayList<>();
        list.add("A");
        list.add("B");

        boolean result = list.containsAll(list);
        System.out.println("Unsafe containsAll (self): " + result);

        list.retainAll(list);
        System.out.println("Unsafe retainAll (self): " + list);
    }

    // Безопасная конструкция — вызовы на другой коллекции
    public static void safeOperations() {
        List<String> list = new ArrayList<>();
        list.add("A");
        list.add("B");

        List<String> other = Arrays.asList("A", "C");

        boolean result = list.containsAll(other);
        System.out.println("Safe containsAll (other): " + result);

        list.retainAll(other);
        System.out.println("Safe retainAll (other): " + list);
    }

    public static void main(String[] args) {
        unsafeOperations();
        safeOperations();
    }
}

