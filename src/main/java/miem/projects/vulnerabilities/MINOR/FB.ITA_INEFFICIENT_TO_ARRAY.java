package miem.projects.vulnerabilities.MINOR.FB;

import java.util.ArrayList;
import java.util.List;

public class FB_ITA_INEFFICIENT_TO_ARRAY {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        List<String> list = new ArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");
        
        // Неэффективное преобразование в массив
        String[] array = list.toArray(new String[0]);
        
        for (String s : array) {
            System.out.println(s);
        }
    }

    public static void correctTest() {
        List<String> list = new ArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");
        
        // Эффективное преобразование с указанием размера
        String[] array = list.toArray(new String[list.size()]);
        
        for (String s : array) {
            System.out.println(s);
        }
    }
}