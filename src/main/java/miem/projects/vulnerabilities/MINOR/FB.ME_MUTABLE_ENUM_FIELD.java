package miem.projects.vulnerabilities.MINOR.FB;

import java.util.ArrayList;
import java.util.List;

public class FB_ME_MUTABLE_ENUM_FIELD {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        // Некорректно: enum с изменяемым полем-коллекцией
        enum Department {
            IT(new ArrayList<>()), HR(new ArrayList<>());
            
            private List<String> employees;
            
            Department(List<String> employees) {
                this.employees = employees;
            }
            
            public List<String> getEmployees() {
                return employees;  // Возвращает изменяемую коллекцию
            }
        }
        
        Department.IT.getEmployees().add("John");  // Модификация enum!
        System.out.println("IT Employees: " + Department.IT.getEmployees());
    }

    public static void correctTest() {
        // Корректно: защитная копия и неизменяемая коллекция
        enum Department {
            IT(List.of()), HR(List.of());
            
            private final List<String> employees;
            
            Department(List<String> employees) {
                this.employees = new ArrayList<>(employees);  // Защитная копия
            }
            
            public List<String> getEmployees() {
                return new ArrayList<>(employees);  // Возвращаем копию
            }
            
            // Или лучше - возвращать неизменяемое представление
            public List<String> getEmployeesUnmodifiable() {
                return List.copyOf(employees);
            }
        }
        
        // Попытка модификации будет неудачной
        List<String> itEmployees = Department.IT.getEmployeesUnmodifiable();
        try {
            itEmployees.add("Mary");  // Выбросит UnsupportedOperationException
        } catch (UnsupportedOperationException e) {
            System.out.println("Properly protected enum field");
        }
    }
}