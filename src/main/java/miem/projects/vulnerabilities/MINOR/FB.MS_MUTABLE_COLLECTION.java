package miem.projects.vulnerabilities.MINOR.FB;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FB_MS_MUTABLE_COLLECTION {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        // Некорректно: возврат изменяемой коллекции
        class Team {
            private List<String> members = new ArrayList<>();
            
            public List<String> getMembers() {
                return members;  // Опасное возвращение ссылки
            }
        }
        
        Team team = new Team();
        team.getMembers().add("Alice");
        team.getMembers().add("Bob");
        
        // Внешний код может модифицировать внутреннюю коллекцию
        team.getMembers().clear();
        System.out.println("Team members (insecure): " + team.getMembers());
    }

    public static void correctTest() {
        // Корректно: защитные копии и неизменяемые коллекции
        class Team {
            private List<String> members = new ArrayList<>();
            
            public List<String> getMembers() {
                return new ArrayList<>(members);  // Возвращаем копию
            }
            
            // Или лучше - возвращать неизменяемое представление
            public List<String> getMembersUnmodifiable() {
                return Collections.unmodifiableList(members);
            }
            
            // Метод для добавления членов с контролем
            public void addMember(String name) {
                members.add(name);
            }
        }
        
        Team team = new Team();
        team.addMember("Alice");
        team.addMember("Bob");
        
        try {
            team.getMembersUnmodifiable().clear();  // Выбросит исключение
        } catch (UnsupportedOperationException e) {
            System.out.println("Properly protected collection");
        }
    }
}