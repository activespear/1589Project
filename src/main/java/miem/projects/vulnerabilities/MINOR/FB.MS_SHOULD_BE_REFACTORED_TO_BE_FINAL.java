package miem.projects.vulnerabilities.MINOR.FB;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FB_MS_SHOULD_BE_REFACTORED_TO_BE_FINAL {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    // Уязвимая реализация с изменяемым статическим полем
    public static class ConfigurationIncorrect {
        public static List<String> PRIVILEGED_USERS = new ArrayList<>();
        
        static {
            PRIVILEGED_USERS.add("admin");
            PRIVILEGED_USERS.add("superuser");
        }
    }

    // Исправленная реализация
    public static class ConfigurationCorrect {
        private static final List<String> PRIVILEGED_USERS;
        
        static {
            List<String> users = new ArrayList<>();
            users.add("admin");
            users.add("superuser");
            PRIVILEGED_USERS = Collections.unmodifiableList(users);
        }
        
        public static List<String> getPrivilegedUsers() {
            return PRIVILEGED_USERS;
        }
    }

    public static void incorrectTest() {
        // Вредоносный код может изменить список привилегированных пользователей
        ConfigurationIncorrect.PRIVILEGED_USERS.add("hacker");
        System.out.println("Incorrect users: " + ConfigurationIncorrect.PRIVILEGED_USERS);
    }

    public static void correctTest() {
        // Попытка изменения приведет к UnsupportedOperationException
        try {
            ConfigurationCorrect.getPrivilegedUsers().add("hacker");
        } catch (UnsupportedOperationException e) {
            System.out.println("Correct behavior: " + e.getMessage());
        }
        System.out.println("Correct users: " + ConfigurationCorrect.getPrivilegedUsers());
    }
}