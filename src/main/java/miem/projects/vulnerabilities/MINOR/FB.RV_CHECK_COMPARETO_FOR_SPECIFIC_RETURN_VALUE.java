package miem.projects.vulnerabilities.MINOR.FB;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RV_CHECK_COMPARETO_FOR_SPECIFIC_RETURN_VALUE {
    public static void main(String[] args) {
        List<Person> people = new ArrayList<>();
        people.add(new Person("Alice", 30));
        people.add(new Person("Bob", 25));
        
        // Некорректное использование compareTo()
        Person first = people.get(0);
        Person second = people.get(1);
        
        // Плохо: проверка конкретного значения
        if (first.compareTo(second) == 1) {  // FB.RV_CHECK_COMPARETO_FOR_SPECIFIC_RETURN_VALUE
            System.out.println("Некорректная проверка: == 1");
        }
        
        // Корректные альтернативы
        if (first.compareTo(second) > 0) {
            System.out.println("Правильная проверка: > 0");
        }
        
        // Сортировка с использованием compareTo()
        Collections.sort(people);
        System.out.println("Отсортированный список: " + people);
    }
}

class Person implements Comparable<Person> {
    private String name;
    private int age;
    
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
    
    @Override
    public int compareTo(Person other) {
        // Сравниваем по возрасту
        return Integer.compare(this.age, other.age);
    }
    
    // Некорректный метод
    public boolean isOlderBad(Person other) {
        return this.compareTo(other) == 1;  // FB.RV_CHECK_COMPARETO_FOR_SPECIFIC_RETURN_VALUE
    }
    
    // Корректный метод
    public boolean isOlderGood(Person other) {
        return this.compareTo(other) > 0;
    }
    
    @Override
    public String toString() {
        return name + "(" + age + ")";
    }
}