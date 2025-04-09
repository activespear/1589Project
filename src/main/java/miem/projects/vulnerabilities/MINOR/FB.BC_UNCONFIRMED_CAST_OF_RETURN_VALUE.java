package miem.projects.vulnerabilities.MINOR.FB;

import java.util.List;

public class FB_BC_UNCONFIRMED_CAST_OF_RETURN_VALUE {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        List<Object> mixedList = List.of("text", 123, new Object());
        
        // Некорректно: непроверенный каст
        for (Object item : mixedList) {
            String str = (String) item; // Опасный каст
            System.out.println(str.length());
        }
    }

    public static void correctTest() {
        List<Object> mixedList = List.of("text", 123, new Object());
        
        // Корректно: проверка типа перед кастом
        for (Object item : mixedList) {
            if (item instanceof String) {
                String str = (String) item;
                System.out.println(str.length());
            } else {
                System.out.println("Not a string: " + item.getClass());
            }
        }
    }
}