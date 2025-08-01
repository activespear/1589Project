package miem.projects.vulnerabilities.MAJOR.FB;

import java.util.*;

public class HE_USE_OF_UNHASHABLE_CLASS {

    // Небезопасная конструкция: нет hashCode, но используется в HashSet
    static class PersonUnsafe {
        private String name;
        private int age;

        public PersonUnsafe(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            PersonUnsafe person = (PersonUnsafe) obj;
            return age == person.age && name.equals(person.name);
        }

        // hashCode отсутствует
    }

    // Безопасная конструкция: реализован и equals, и hashCode
    static class PersonSafe {
        private String name;
        private int age;

        public PersonSafe(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            PersonSafe person = (PersonSafe) obj;
            return age == person.age && name.equals(person.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, age);
        }
    }

    // Запуск небезопасной версии
    public static void runUnsafe() {
        Set<PersonUnsafe> people = new HashSet<>();
        people.add(new PersonUnsafe("John", 25));
        System.out.println("Unsafe contains: " + people.contains(new PersonUnsafe("John", 25))); // false
    }

    // Запуск безопасной версии
    public static void runSafe() {
        Set<PersonSafe> people = new HashSet<>();
        people.add(new PersonSafe("John", 25));
        System.out.println("Safe contains: " + people.contains(new PersonSafe("John", 25))); // true
    }

    public static void main(String[] args) {
        System.out.println("Running unsafe version:");
        runUnsafe();

        System.out.println("\nRunning safe version:");
        runSafe();
    }
}

