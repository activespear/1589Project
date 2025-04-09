package miem.projects.vulnerabilities.MINOR.FB;

import java.util.ArrayList;
import java.util.List;

public class MS_EXPOSE_REP {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    // Некорректный возврат ссылки на изменяемую коллекцию
    public static void incorrectTest() {
        StudentRegistry registry = new StudentRegistry();
        registry.addStudent("Alice");
        registry.addStudent("Bob");

        List<String> students = registry.getStudents(); // ОШИБКА: возврат ссылки на внутренний список
        students.add("Mallory"); // Несанкционированное изменение состояния
        System.out.println("Modified student list (INCORRECT): " + registry.getStudents());
    }

    // Корректный способ защиты внутренней коллекции
    public static void correctTest() {
        StudentRegistryFixed registry = new StudentRegistryFixed();
        registry.addStudent("Alice");
        registry.addStudent("Bob");

        List<String> students = registry.getStudents(); // Возвращает неизменяемую копию
        try {
            students.add("Mallory"); // Выбросит UnsupportedOperationException
        } catch (UnsupportedOperationException e) {
            System.out.println("Original student list preserved (CORRECT): " + registry.getStudents());
        }
    }
}

// Некорректный класс (уязвимый)
class StudentRegistry {
    private List<String> students = new ArrayList<>();

    public void addStudent(String name) {
        students.add(name);
    }

    public List<String> getStudents() {
        return students; // ОШИБКА: возврат ссылки на изменяемую коллекцию
    }
}

// Исправленный класс (защищенный)
class StudentRegistryFixed {
    private List<String> students = new ArrayList<>();

    public void addStudent(String name) {
        students.add(name);
    }

    public List<String> getStudents() {
        return List.copyOf(students); // Возвращает неизменяемую копию
    }
}