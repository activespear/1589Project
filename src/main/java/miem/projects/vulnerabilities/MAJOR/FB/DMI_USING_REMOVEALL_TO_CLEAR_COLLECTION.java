package miem.projects.vulnerabilities.MAJOR.FB;

import java.util.ArrayList;
import java.util.Collection;

public class DMI_USING_REMOVEALL_TO_CLEAR_COLLECTION {

    // Небезопасная конструкция — удаление всех элементов с помощью removeAll(c)
    public static void unsafeClear() {
        Collection<String> c = new ArrayList<>();
        c.add("A");
        c.add("B");
        System.out.println("Before unsafeClear: " + c);
        c.removeAll(c);
        System.out.println("After unsafeClear: " + c);
    }

    // Безопасная конструкция — использование clear()
    public static void safeClear() {
        Collection<String> c = new ArrayList<>();
        c.add("A");
        c.add("B");
        System.out.println("Before safeClear: " + c);
        c.clear();
        System.out.println("After safeClear: " + c);
    }

    public static void main(String[] args) {
        unsafeClear();
        safeClear();
    }
}

