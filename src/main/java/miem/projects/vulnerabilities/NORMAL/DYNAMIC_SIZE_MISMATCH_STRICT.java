package miem.projects.vulnerabilities.NORMAL;

import java.util.ArrayList;
import java.util.List;

public class DYNAMIC_SIZE_MISMATCH_STRICT {
    public static void main(String[] args) {
        incorrectTest(2);
        correctTest(2);
    }

    public static void incorrectTest(int index) {
        List<Integer> numbers = new ArrayList<>();
        numbers.add(1);
        numbers.add(2);

        // Возможно IndexOutOfBoundsException
        int value = numbers.get(index);
        System.out.println(value);
    }

    public static void correctTest(int index) {
        List<Integer> numbers = new ArrayList<>();
        numbers.add(1);
        numbers.add(2);

        if (index < numbers.size()) {
            int value = numbers.get(index);
            System.out.println(value);
        } else {
            System.out.println("Ошибка: индекс выходит за пределы списка.");
        }
    }
}